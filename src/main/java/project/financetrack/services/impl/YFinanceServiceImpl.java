package project.financetrack.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.financetrack.dtos.investment.InvestmentDTO;
import project.financetrack.services.ExternalInvestmentService;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service("yFinanceServiceImpl")
@RequiredArgsConstructor
public class YFinanceServiceImpl implements ExternalInvestmentService {

    public List<InvestmentDTO> completeInvestmentData(List<InvestmentDTO> investments) throws IOException {
        List<String> symbols = new ArrayList<>();

        if (investments.isEmpty()) {
            return List.of();
        }

        for (InvestmentDTO investment : investments) {
            symbols.add(investment.getTickerSymbol());
        }

        String[] symbolsArray = symbols.toArray(new String[0]);
        var stocksMap = YahooFinance.get(symbolsArray);

        for (InvestmentDTO investment : investments) {
            Stock stock = stocksMap.get(investment.getTickerSymbol());
            if (stock != null) {
                // Nombre de la compañía
                investment.setName(stock.getName());

                // Precio actual
                BigDecimal price = stock.getQuote().getPrice();
                investment.setPrice(price.doubleValue());

                // Valor total (precio * cantidad)
                BigDecimal value = price.multiply(BigDecimal.valueOf(investment.getQuantity()));
                investment.setValue(value.doubleValue());

                // Cambio en valor monetario
                BigDecimal change = stock.getQuote().getChange();
                investment.setChange(change != null ? change.doubleValue() : 0.0);

                // Cambio porcentual
                BigDecimal changePercent = stock.getQuote().getChangeInPercent();
                investment.setChangePercentage(changePercent != null ?
                        changePercent.setScale(2, RoundingMode.HALF_EVEN).doubleValue() : 0.0);
            }
        }

        return investments;
    }

    /**
     * Verifica si un símbolo bursátil existe y devuelve sus datos
     *
     * @param tickerSymbol Símbolo a verificar
     * @return Investment con los datos del símbolo si existe, null si no existe
     * @throws IOException Si hay problemas al consultar la API
     */
    public InvestmentDTO validateAndGetSymbolData(String tickerSymbol, Integer quantity) throws IOException {
        Stock stock = YahooFinance.get(tickerSymbol);

        // Verificamos si el símbolo existe
        if (stock != null && stock.getName() != null && stock.getQuote().getPrice() != null) {
            // Crear un objeto Investment con los datos obtenidos
            InvestmentDTO investment = new InvestmentDTO();
            investment.setTickerSymbol(tickerSymbol);
            investment.setQuantity(quantity); // Cantidad por defecto

            // Nombre de la compañia
            investment.setName(stock.getName());

            // Precio actual
            BigDecimal price = stock.getQuote().getPrice();
            investment.setPrice(price.doubleValue());

            // Valor igual al precio para una unidad
            investment.setValue(price.doubleValue());

            // Cambio en valor monetario
            BigDecimal change = stock.getQuote().getChange();
            investment.setChange(change != null ? change.doubleValue() : 0.0);

            // Cambio porcentual
            BigDecimal changePercent = stock.getQuote().getChangeInPercent();
            investment.setChangePercentage(changePercent != null ?
                    changePercent.setScale(2, RoundingMode.HALF_EVEN).doubleValue() : 0.0);

            return investment;
        }

        throw new IllegalArgumentException("The ticker symbol sended do not exist");
    }
}

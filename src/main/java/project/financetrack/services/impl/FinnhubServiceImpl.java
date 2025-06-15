package project.financetrack.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import project.financetrack.dtos.investment.InvestmentDTO;
import project.financetrack.services.ExternalInvestmentService;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service("finnhubServiceImpl")
@RequiredArgsConstructor
public class FinnhubServiceImpl implements ExternalInvestmentService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${finnhub.api.key}")
    private String apiKey;

    private final String FINNHUB_BASE_URL = "https://finnhub.io/api/v1";

    /**
     * Completa los datos de una lista de inversiones con información de Finnhub
     */
    public List<InvestmentDTO> completeInvestmentData(List<InvestmentDTO> investmentDTOs) throws IOException {
        for (InvestmentDTO investmentDTO : investmentDTOs) {
            completeInvestmentDTOData(investmentDTO);
        }
        return investmentDTOs;
    }

    /**
     * Completa los datos de una sola inversión con información de Finnhub
     */
    private InvestmentDTO completeInvestmentDTOData(InvestmentDTO investmentDTO) throws IOException {
        String tickerSymbol = investmentDTO.getTickerSymbol();

        // Obtener datos de cotización
        JsonNode quoteData = getQuoteData(tickerSymbol);
        if (quoteData != null) {
            // Precio actual
            double price = quoteData.get("c").asDouble();
            investmentDTO.setPrice(price);

            // Valor total (precio * cantidad)
            double value = price * investmentDTO.getQuantity();
            investmentDTO.setValue(value);

            // Cambio en valor monetario (precio actual - precio de cierre anterior)
            double previousClose = quoteData.get("pc").asDouble();
            double change = price - previousClose;
            investmentDTO.setChange(new BigDecimal(change).setScale(2, RoundingMode.HALF_EVEN).doubleValue());

            // Cambio porcentual
            double changePercentage = (change / previousClose) * 100;
            investmentDTO.setChangePercentage(new BigDecimal(changePercentage).setScale(2, RoundingMode.HALF_EVEN).doubleValue());
        }

        // Obtener datos de compañía (para el nombre)
        JsonNode companyData = getCompanyData(tickerSymbol);
        if (companyData != null && companyData.has("name")) {
            investmentDTO.setName(companyData.get("name").asText());
        } else {
            // Si no encontramos el nombre, usamos el símbolo como respaldo
            investmentDTO.setName(tickerSymbol);
        }

        return investmentDTO;
    }

    /**
     * Verifica si un símbolo bursátil existe y devuelve sus datos
     */
    public InvestmentDTO validateAndGetSymbolData(String tickerSymbol, Double quantity) throws IOException {
        // Verificar si el símbolo existe

        if (tickerSymbol.isBlank()) {
            throw new IllegalArgumentException();
        }

        JsonNode quoteData = getQuoteData(tickerSymbol);

        if (quoteData != null && quoteData.has("c") && quoteData.get("c").asDouble() > 0) {
            // Crear un InvestmentDTO con los datos básicos
            InvestmentDTO investmentDTO = new InvestmentDTO();
            investmentDTO.setTickerSymbol(tickerSymbol);
            investmentDTO.setQuantity(quantity);

            // Completar con datos de Finnhub
            return completeInvestmentDTOData(investmentDTO);
        }

        throw new IllegalArgumentException("The ticker symbol sended do not exist");
    }

    /**
     * Obtiene datos de cotización de Finnhub
     */
    private JsonNode getQuoteData(String symbol) throws IOException {
        String url = UriComponentsBuilder.fromHttpUrl(FINNHUB_BASE_URL + "/quote")
                .queryParam("symbol", symbol)
                .queryParam("token", apiKey)
                .toUriString();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            JsonNode root = objectMapper.readTree(response.getBody());

            // Verificar si la respuesta tiene datos válidos
            if (root != null && root.has("c") && !root.get("c").isNull()) {
                return root;
            }
        }

        throw new IllegalArgumentException("The ticker symbol sended do not exist");
    }

    /**
     * Obtiene datos de la compañía de Finnhub
     */
    private JsonNode getCompanyData(String symbol) throws IOException {
        String url = UriComponentsBuilder.fromHttpUrl(FINNHUB_BASE_URL + "/stock/profile2")
                .queryParam("symbol", symbol)
                .queryParam("token", apiKey)
                .toUriString();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return objectMapper.readTree(response.getBody());
        }

        throw new IllegalArgumentException("The ticker symbol sended do not exist");
    }
}

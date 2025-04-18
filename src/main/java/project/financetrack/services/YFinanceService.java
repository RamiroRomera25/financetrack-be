package project.financetrack.services;

import org.springframework.stereotype.Service;
import project.financetrack.dtos.investment.InvestmentDTO;

import java.io.IOException;
import java.util.List;

@Service
public interface YFinanceService {
    InvestmentDTO validateAndGetSymbolData(String tickerSymbol, Integer quantity) throws IOException;
    List<InvestmentDTO> completeInvestmentData(List<InvestmentDTO> investments) throws IOException;
}

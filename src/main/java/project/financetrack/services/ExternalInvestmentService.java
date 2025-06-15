package project.financetrack.services;

import org.springframework.stereotype.Service;
import project.financetrack.dtos.investment.InvestmentDTO;

import java.io.IOException;
import java.util.List;

@Service
public interface ExternalInvestmentService {
    InvestmentDTO validateAndGetSymbolData(String tickerSymbol, Double quantity) throws IOException;
    List<InvestmentDTO> completeInvestmentData(List<InvestmentDTO> investments) throws IOException;
}

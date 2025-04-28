package project.financetrack.dtos.investment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvestmentDTO {
    private Long id;
    private String tickerSymbol;
    private String name;
    private Integer quantity;
    private Double price;
    private Double value;
    private Double change;
    private Double changePercentage;
}

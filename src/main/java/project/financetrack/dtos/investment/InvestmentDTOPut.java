package project.financetrack.dtos.investment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.financetrack.entities.base.BaseEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentDTOPut {
    private Integer quantity;
}

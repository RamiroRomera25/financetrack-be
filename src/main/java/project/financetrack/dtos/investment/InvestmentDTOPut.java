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
public class InvestmentDTOPut extends BaseEntity {
    private Long investmentId;

    private Integer quantity;
}

package project.financetrack.dtos.goal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.financetrack.entities.base.BaseEntity;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoalDTOPut extends BaseEntity {
    private String objective;

    private LocalDate endDate;

    private Integer quantity;
}

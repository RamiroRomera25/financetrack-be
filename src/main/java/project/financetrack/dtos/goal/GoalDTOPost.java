package project.financetrack.dtos.goal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoalDTOPost {
    private String objective;

    private LocalDate endDate;

    private Integer quantity;

    private Long projectId;
}

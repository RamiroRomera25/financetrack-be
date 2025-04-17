package project.financetrack.dtos.maturity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaturityDTOPost {
    private Integer quantity;

    private LocalDate endDate;

    private Long projectId;
}

package project.financetrack.dtos.maturity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.financetrack.enums.MaturityState;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaturityDTOPut {
    private Integer quantity;

    private LocalDate endDate;

    private MaturityState state;
}

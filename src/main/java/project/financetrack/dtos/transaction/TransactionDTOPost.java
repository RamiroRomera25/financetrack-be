package project.financetrack.dtos.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTOPost {

    private Integer quantity;

    private Long categoryId;

    private Long projectId;
}

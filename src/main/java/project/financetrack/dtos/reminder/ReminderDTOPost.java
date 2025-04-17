package project.financetrack.dtos.reminder;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReminderDTOPost  {
    @NotNull
    private LocalDate reminderDate;

    @NotNull
    private String subject;

    @NotNull
    private Long projectId;
}

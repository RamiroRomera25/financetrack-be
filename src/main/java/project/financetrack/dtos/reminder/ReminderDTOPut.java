package project.financetrack.dtos.reminder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReminderDTOPut {
    private LocalDate reminderDate;

    private String subject;
}

package project.financetrack.dtos.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.financetrack.enums.EmailType;

import java.util.Map;

/**
 * Data Transfer Object (DTO) representing the creation of an email.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmailDTO {
    private String recipient;

    private Map<String, String> variables;

    private EmailType emailType;
}



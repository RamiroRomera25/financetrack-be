package project.financetrack.dtos.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EmailTemplate class.
 * <p>
 * This class represents an email with its name and body.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailTemplate {

    /**
     * Identification number.
     */
    private Long id;

    /**
     * The email's name.
     */
    private String name;

    /**
     * What the email's body will be.
     */
    private String body;
}

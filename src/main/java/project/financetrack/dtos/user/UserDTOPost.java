package project.financetrack.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The {@code UserDTOPost} class represents a data transfer object for user requests.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTOPost {

    /**
     * First name of the owner of the user.
     */
    @JsonProperty(value = "first_name")
    @NotNull(message = "First Name cannot be null")
    private String firstName;

    /**
     * Second name of the owner of the user.
     */
    @JsonProperty(value = "last_name")
    @NotNull(message = "Last Name cannot be null")
    private String lastName;

    /**
     * Email of the user.
     */
    @JsonProperty(value = "email")
    @NotNull(message = "Email cannot be null")
    @Email
    private String email;


    @JsonProperty(value = "password")
    @NotNull(message = "Email cannot be null")
    private String password;
}


package project.financetrack.dtos.user.login;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for user login requests.
 * This class encapsulates the data required for a user to log in,
 * including the user's email and password.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    /**
     * The email of the user attempting to log in.
     */
    @JsonProperty("email")
    @NotBlank(message = "Email must be not empty.")
    private String email;

    /**
     * The password of the user attempting to log in.
     */
    @JsonProperty("password")
    @NotBlank(message = "Password must be not empty.")
    private String password;
}

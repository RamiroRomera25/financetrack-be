package project.financetrack.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UserDTOPut {

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
}


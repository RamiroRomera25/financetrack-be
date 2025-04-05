package project.financetrack.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.financetrack.entities.UserEntity;

import java.util.List;
import java.util.UUID;

/**
 * This class is returned by the user endpoint.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {


    private Long id;

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
    @Email
    @NotNull(message = "Email cannot be null")
    private String email;

    @JsonProperty(value = "is_active")
    private boolean isActive;

    public UserDTO(UserEntity entity) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
    }
}

package project.financetrack.dtos.user.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for changing a user's password.
 * This class encapsulates the data required to update a user's password,
 * including the user's ID, old password, and new password.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {

    /**
     * The ID of the user whose password is being changed.
     */
    @NotNull(message = "User ID is required")
    @JsonProperty("user_id")
    private Long userId;

    /**
     * The current password of the user, used for verification.
     */
    @NotNull(message = "Old password is required")
    @JsonProperty("old_password")
    private String oldPassword;

    /**
     * The new password to be set for the user.
     */
    @NotNull(message = "New password is required")
    @JsonProperty("new_password")
    private String newPassword;
}

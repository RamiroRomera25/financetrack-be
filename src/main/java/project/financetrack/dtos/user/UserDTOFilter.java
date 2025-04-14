package project.financetrack.dtos.user;

import lombok.Data;

/**
 * The {@code UsersFilter} class is used to filter user data based on various criteria.
 */
@Data
public class UserDTOFilter {
    /**
     * Indicates whether the user is active.
     */
    private Boolean isActive;

    /**
     * Search value for dynamic filtering.
     */
    private String searchValue;
}

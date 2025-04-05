package project.financetrack.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import project.financetrack.dtos.user.UserDTOPost;
import project.financetrack.entities.base.BaseEntity;

/**
 * The {@code UserEntity} class represents a user of the app.
 * It references to a table named "users".
 */
//@Audited
//@AuditTable(value = "users_audit")
@EqualsAndHashCode(callSuper = true)
@Table(name = "users")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity {

    /**
     * Unique identifier for the turn.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * First name of the owner of the user.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Second name of the owner of the user.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Password of the user.
     */
    @Column(name = "password")
    private String password;

    /**
     * Email of the user.
     */
    @Column(name = "email")
    private String email;

    public UserEntity(UserDTOPost dtoPost) {
        this.email = dtoPost.getEmail();
        this.firstName = dtoPost.getFirstName();
        this.lastName = dtoPost.getLastName();
    }
}

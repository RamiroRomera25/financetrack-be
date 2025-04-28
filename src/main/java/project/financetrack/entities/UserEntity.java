package project.financetrack.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import project.financetrack.dtos.user.UserDTOPost;
import project.financetrack.entities.base.BaseEntity;

import java.util.List;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    private Boolean premium = false;

    @OneToMany
    private List<ProjectEntity> projects;

    public UserEntity(UserDTOPost dtoPost) {
        this.email = dtoPost.getEmail();
        this.firstName = dtoPost.getFirstName();
        this.lastName = dtoPost.getLastName();
    }
}

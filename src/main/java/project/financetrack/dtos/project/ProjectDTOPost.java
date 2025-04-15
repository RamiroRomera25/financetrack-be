package project.financetrack.dtos.project;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import project.financetrack.entities.UserEntity;
import project.financetrack.entities.base.BaseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTOPost extends BaseEntity {

    private String name;

    private Long userId;

}

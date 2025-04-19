package project.financetrack.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import project.financetrack.entities.base.BaseEntity;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Table(name = "goals")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoalEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String objective;

    private LocalDate endDate;

    private Integer quantity;

    private String notes;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private ProjectEntity project;
}

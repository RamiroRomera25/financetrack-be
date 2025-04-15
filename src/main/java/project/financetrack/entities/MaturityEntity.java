package project.financetrack.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import project.financetrack.enums.MaturityState;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Table(name = "maturities")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaturityEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private MaturityState state;

    @ManyToOne
    @JoinColumn
    private ProjectEntity project;
}

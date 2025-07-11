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

@EqualsAndHashCode(callSuper = true)
@Table(name = "investments")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tickerSymbol;

    private Double quantity;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private ProjectEntity project;
}

package project.financetrack.dtos.investment;

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
import project.financetrack.entities.ProjectEntity;
import project.financetrack.entities.base.BaseEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentDTOPost extends BaseEntity {
    private String tickerSymbol;

    private Integer quantity;

    private Long projectId;
}

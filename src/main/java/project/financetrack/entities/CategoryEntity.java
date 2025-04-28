package project.financetrack.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import project.financetrack.entities.base.BaseEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Table(name = "categories")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String color;

    @OneToMany(mappedBy = "category", orphanRemoval = true)
    @JsonIgnoreProperties("category")
    private List<TransactionEntity> transactions;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private ProjectEntity project;
}

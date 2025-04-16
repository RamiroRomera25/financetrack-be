package project.financetrack.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "projects")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn
    private List<CategoryEntity> categories;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn
    private List<GoalEntity> goals;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn
    private List<InvestmentEntity> investments;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn
    private List<MaturityEntity> maturities;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn
//    private List<> reminders;

}

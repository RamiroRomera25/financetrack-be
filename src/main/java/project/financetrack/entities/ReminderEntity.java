package project.financetrack.entities;

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
@Table(name = "reminders")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReminderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate reminderDate;

    private String subject;

    @ManyToOne
    @JoinColumn
    private ProjectEntity project;
}

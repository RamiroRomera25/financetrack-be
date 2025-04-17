package project.financetrack.repositories;

import org.springframework.stereotype.Repository;
import project.financetrack.entities.ReminderEntity;

@Repository
public interface ReminderRepository extends GenericRepository<ReminderEntity, Long> {
}

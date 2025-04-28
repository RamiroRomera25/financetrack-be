package project.financetrack.repositories;

import org.springframework.stereotype.Repository;
import project.financetrack.entities.GoalEntity;

@Repository
public interface GoalRepository extends GenericRepository<GoalEntity, Long> {
}

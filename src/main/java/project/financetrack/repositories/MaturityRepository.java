package project.financetrack.repositories;

import org.springframework.stereotype.Repository;
import project.financetrack.entities.MaturityEntity;

@Repository
public interface MaturityRepository extends GenericRepository<MaturityEntity, Long> {
}

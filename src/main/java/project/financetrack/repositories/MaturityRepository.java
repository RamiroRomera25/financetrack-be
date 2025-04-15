package project.financetrack.repositories;

import org.springframework.stereotype.Repository;
import project.financetrack.entities.MaturityEntity;
import project.financetrack.entities.ProjectEntity;

@Repository
public interface MaturityRepository extends GenericRepository<MaturityEntity, Long> {
}

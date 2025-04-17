package project.financetrack.repositories;

import org.springframework.stereotype.Repository;
import project.financetrack.entities.ProjectEntity;

@Repository
public interface ProjectRepository extends GenericRepository<ProjectEntity, Long> {
}

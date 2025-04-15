package project.financetrack.repositories;

import org.springframework.stereotype.Repository;
import project.financetrack.entities.ProjectEntity;
import project.financetrack.entities.UserEntity;

import java.util.Optional;

@Repository
public interface ProjectRepository extends GenericRepository<ProjectEntity, Long> {
}

package project.financetrack.repositories;

import org.springframework.stereotype.Repository;
import project.financetrack.entities.DummyEntity;

@Repository
public interface DummyRepository extends GenericRepository<DummyEntity, Long> {
}

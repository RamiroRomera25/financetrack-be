package project.financetrack.repositories;

import org.springframework.stereotype.Repository;
import project.financetrack.entities.CategoryEntity;

@Repository
public interface CategoryRepository extends GenericRepository<CategoryEntity, Long> {
}

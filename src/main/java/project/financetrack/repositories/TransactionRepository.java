package project.financetrack.repositories;

import org.springframework.stereotype.Repository;
import project.financetrack.entities.CategoryEntity;
import project.financetrack.entities.TransactionEntity;

@Repository
public interface TransactionRepository extends GenericRepository<TransactionEntity, Long> {
}

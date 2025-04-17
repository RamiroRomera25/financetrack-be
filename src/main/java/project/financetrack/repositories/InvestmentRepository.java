package project.financetrack.repositories;

import org.springframework.stereotype.Repository;
import project.financetrack.entities.InvestmentEntity;

@Repository
public interface InvestmentRepository extends GenericRepository<InvestmentEntity, Long> {
}

package project.financetrack.services;

import org.springframework.stereotype.Service;
import project.financetrack.dtos.investment.InvestmentDTOFilter;
import project.financetrack.dtos.investment.InvestmentDTOPost;
import project.financetrack.entities.InvestmentEntity;
import project.financetrack.repositories.specs.SpecificationBuilder;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceCreate;
import project.financetrack.services.genericSegregation.filters.ServiceGetAllListFilter;

@Service
public interface InvestmentService extends
        ServiceCreate<InvestmentEntity, Long, InvestmentEntity, InvestmentDTOPost>
{
    SpecificationBuilder<InvestmentEntity> specificationBuilder();
}

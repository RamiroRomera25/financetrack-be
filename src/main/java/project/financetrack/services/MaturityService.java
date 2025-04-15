package project.financetrack.services;

import org.springframework.stereotype.Service;
import project.financetrack.dtos.maturity.MaturityDTOFilter;
import project.financetrack.dtos.maturity.MaturityDTOPost;
import project.financetrack.entities.MaturityEntity;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceCreate;
import project.financetrack.services.genericSegregation.compositeUniqueAtt.ServiceGetByCompositeUniqueAtt;
import project.financetrack.services.genericSegregation.filters.ServiceGetAllListFilter;
import project.financetrack.services.genericSegregation.uniqueAtt.ServiceGetByUniqueAtt;

@Service
public interface MaturityService extends
        ServiceCreate<MaturityEntity, Long, MaturityEntity, MaturityDTOPost>,
        ServiceGetAllListFilter<MaturityEntity, Long, MaturityEntity, MaturityDTOFilter>,
        ServiceGetByUniqueAtt<MaturityEntity, Long, MaturityEntity>
{



}

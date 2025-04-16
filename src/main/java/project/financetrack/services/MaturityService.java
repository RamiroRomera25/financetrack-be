package project.financetrack.services;

import org.springframework.stereotype.Service;
import project.financetrack.dtos.goal.GoalDTOPut;
import project.financetrack.dtos.maturity.MaturityDTOFilter;
import project.financetrack.dtos.maturity.MaturityDTOPost;
import project.financetrack.dtos.maturity.MaturityDTOPut;
import project.financetrack.entities.GoalEntity;
import project.financetrack.entities.MaturityEntity;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceCreate;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceGetById;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceSoftDelete;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceUpdate;
import project.financetrack.services.genericSegregation.compositeUniqueAtt.ServiceGetByCompositeUniqueAtt;
import project.financetrack.services.genericSegregation.filters.ServiceGetAllListFilter;
import project.financetrack.services.genericSegregation.uniqueAtt.ServiceGetAllByUniqueAtt;
import project.financetrack.services.genericSegregation.uniqueAtt.ServiceGetByUniqueAtt;

@Service
public interface MaturityService extends
        ServiceCreate<MaturityEntity, Long, MaturityEntity, MaturityDTOPost>,
        ServiceGetByUniqueAtt<MaturityEntity, Long, MaturityEntity>,
        ServiceGetAllByUniqueAtt<MaturityEntity, Long, MaturityEntity>,
        ServiceGetById<MaturityEntity, Long, MaturityEntity>,
        ServiceSoftDelete<MaturityEntity, Long, MaturityEntity>,
        ServiceUpdate<MaturityEntity, Long, MaturityEntity, MaturityDTOPut>
{
}

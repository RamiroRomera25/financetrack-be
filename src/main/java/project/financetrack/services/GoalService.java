package project.financetrack.services;

import org.springframework.stereotype.Service;
import project.financetrack.dtos.goal.GoalDTOFilter;
import project.financetrack.dtos.goal.GoalDTOPost;
import project.financetrack.dtos.goal.GoalDTOPut;
import project.financetrack.entities.GoalEntity;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceCreate;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceGetById;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceSoftDelete;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceUpdate;
import project.financetrack.services.genericSegregation.filters.ServiceGetAllListFilter;
import project.financetrack.services.genericSegregation.uniqueAtt.ServiceGetAllByUniqueAtt;

@Service
public interface GoalService extends
        ServiceCreate<GoalEntity, Long, GoalEntity, GoalDTOPost>,
        ServiceGetAllListFilter<GoalEntity, Long, GoalEntity, GoalDTOFilter>,
        ServiceGetAllByUniqueAtt<GoalEntity, Long, GoalEntity>,
        ServiceGetById<GoalEntity, Long, GoalEntity>,
        ServiceSoftDelete<GoalEntity, Long, GoalEntity>,
        ServiceUpdate<GoalEntity, Long, GoalEntity, GoalDTOPut>
{
}

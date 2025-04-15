package project.financetrack.services;

import org.springframework.stereotype.Service;
import project.financetrack.dtos.goal.GoalDTOFilter;
import project.financetrack.dtos.goal.GoalDTOPost;
import project.financetrack.entities.GoalEntity;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceCreate;
import project.financetrack.services.genericSegregation.filters.ServiceGetAllListFilter;

@Service
public interface GoalService extends
        ServiceCreate<GoalEntity, Long, GoalEntity, GoalDTOPost>,
        ServiceGetAllListFilter<GoalEntity, Long, GoalEntity, GoalDTOFilter> {
}

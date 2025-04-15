package project.financetrack.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.financetrack.controllers.genericSegregation.basicCRUD.ControllerCreate;
import project.financetrack.controllers.genericSegregation.filters.ControllerGetAllListFilter;
import project.financetrack.dtos.goal.GoalDTOFilter;
import project.financetrack.dtos.goal.GoalDTOPost;
import project.financetrack.entities.GoalEntity;
import project.financetrack.services.GoalService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/goals")
public class GoalController implements
        ControllerCreate<GoalEntity, Long, GoalEntity, GoalDTOPost, GoalService>,
        ControllerGetAllListFilter<GoalEntity, Long, GoalEntity, GoalDTOFilter, GoalService>
{
    private final GoalService goalService;

    @Override
    public GoalService getService() {
        return goalService;
    }
}

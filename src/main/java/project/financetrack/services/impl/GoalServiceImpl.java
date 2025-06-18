package project.financetrack.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.financetrack.dtos.goal.GoalDTOPost;
import project.financetrack.entities.GoalEntity;
import project.financetrack.repositories.GenericRepository;
import project.financetrack.repositories.GoalRepository;
import project.financetrack.repositories.specs.SpecificationBuilder;
import project.financetrack.services.GoalService;
import project.financetrack.services.ProjectService;

@Service
@RequiredArgsConstructor
public class GoalServiceImpl implements GoalService {

    private final ModelMapper modelMapper;

    private final GoalRepository goalRepository;

    private final ProjectService projectService;

    private final SpecificationBuilder<GoalEntity> specificationBuilder;

    @Override
    public GoalEntity create(GoalDTOPost dtoPost) {
        GoalEntity goal = GoalEntity.builder()
                .project(projectService.getById(dtoPost.getProjectId()))
                .endDate(dtoPost.getEndDate())
                .quantity(dtoPost.getQuantity())
                .objective(dtoPost.getObjective())
                .notes(dtoPost.getNotes())
                .build();

        return GoalService.super.createWithEntity(goal);
    }

    @Override
    public ModelMapper getMapper() {
        return modelMapper;
    }

    @Override
    public GenericRepository<GoalEntity, Long> getRepository() {
        return goalRepository;
    }

    @Override
    public SpecificationBuilder<GoalEntity> specificationBuilder() {
        return specificationBuilder;
    }

    @Override
    public Class<GoalEntity> entityClass() {
        return GoalEntity.class;
    }

    @Override
    public Class<GoalEntity> modelClass() {
        return GoalEntity.class;
    }
}

package project.financetrack.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.financetrack.dtos.project.ProjectDTOPost;
import project.financetrack.entities.ProjectEntity;
import project.financetrack.entities.UserEntity;
import project.financetrack.repositories.GenericRepository;
import project.financetrack.repositories.ProjectRepository;
import project.financetrack.repositories.specs.SpecificationBuilder;
import project.financetrack.services.ProjectService;
import project.financetrack.services.UserService;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ModelMapper modelMapper;

    private final ProjectRepository projectRepository;

    private final UserService userService;

    private final SpecificationBuilder<ProjectEntity> specificationBuilder;


    @Override
    public ProjectEntity create(ProjectDTOPost dtoPost) {
        Map att = Map.of(
            "name", dtoPost.getName(),
            "user.id", dtoPost.getUserId(),
            "isActive", true
        );

        if (ProjectService.super.getOptionalByCompositeUniqueFields(att).isPresent()) {
            throw new IllegalArgumentException("Project name duplicate.");
        }

        UserEntity user = this.userService.getByUniqueField("id", dtoPost.getUserId());

        if (!user.getPremium() && user.getProjects().size() >= 3) {
            throw new IllegalArgumentException("To have more projects buy premium");
        }

        ProjectEntity project = ProjectEntity.builder()
                .name(dtoPost.getName())
                .user(user)
                .build();

        return ProjectService.super.createWithEntity(project);
    }

    @Override
    public ProjectEntity getById(Long id) {


        return ProjectService.super.getById(id);
    }

    @Override
    public ModelMapper getMapper() {
        return modelMapper;
    }

    @Override
    public GenericRepository<ProjectEntity, Long> getRepository() {
        return projectRepository;
    }

    @Override
    public Class<ProjectEntity> entityClass() {
        return ProjectEntity.class;
    }

    @Override
    public Class<ProjectEntity> modelClass() {
        return ProjectEntity.class;
    }

    @Override
    public SpecificationBuilder<ProjectEntity> specificationBuilder() {
        return specificationBuilder;
    }
}

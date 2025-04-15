package project.financetrack.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.financetrack.controllers.genericSegregation.basicCRUD.ControllerCreate;
import project.financetrack.controllers.genericSegregation.basicCRUD.ControllerGetById;
import project.financetrack.controllers.genericSegregation.compositeUniqueAtt.ControllerGetByCompositeUniqueAtt;
import project.financetrack.dtos.project.ProjectDTOPost;
import project.financetrack.entities.ProjectEntity;
import project.financetrack.services.ProjectService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects")
public class ProjectController
implements
        ControllerCreate<ProjectEntity, Long, ProjectEntity, ProjectDTOPost, ProjectService>,
        ControllerGetById<ProjectEntity, Long, ProjectEntity, ProjectService>,
        ControllerGetByCompositeUniqueAtt<ProjectEntity, Long, ProjectEntity, ProjectService>
{

    private final ProjectService projectService;

    @Override
    public ProjectService getService() {
        return projectService;
    }
}

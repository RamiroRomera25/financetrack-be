package project.financetrack.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.financetrack.controllers.genericSegregation.basicCRUD.ControllerCreate;
import project.financetrack.controllers.genericSegregation.basicCRUD.ControllerGetById;
import project.financetrack.controllers.genericSegregation.compositeUniqueAtt.ControllerGetAllByCompositeUniqueAtt;
import project.financetrack.controllers.genericSegregation.compositeUniqueAtt.ControllerGetByCompositeUniqueAtt;
import project.financetrack.controllers.genericSegregation.uniqueAtt.ControllerGetAllByUniqueAtt;
import project.financetrack.controllers.genericSegregation.uniqueAtt.ControllerGetByUniqueAtt;
import project.financetrack.dtos.project.ProjectDTOPost;
import project.financetrack.entities.ProjectEntity;
import project.financetrack.security.jwt.JwtService;
import project.financetrack.services.ProjectService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects")
public class ProjectController
implements
        ControllerGetById<ProjectEntity, Long, ProjectEntity, ProjectService>,
        ControllerGetByCompositeUniqueAtt<ProjectEntity, Long, ProjectEntity, ProjectService>,
        ControllerGetByUniqueAtt<ProjectEntity, Long, ProjectEntity, ProjectService>
{

    private final ProjectService projectService;

    private final JwtService jwtService;

    @Override
    public ProjectService getService() {
        return projectService;
    }

    @GetMapping("/user/list")
    public ResponseEntity<List<ProjectEntity>> getAllProjectByUser(Authentication auth) {
        Long userId = this.jwtService.extractId(auth);

        return ResponseEntity.ok(projectService.getAllByUniqueFields("user.id", userId));
    }

    @PostMapping()
    public ResponseEntity<ProjectEntity> create(@RequestBody @Valid ProjectDTOPost dtoPost, Authentication auth) {
        dtoPost.setUserId(jwtService.extractId(auth));

        return ResponseEntity.ok(projectService.create(dtoPost));
    }
}

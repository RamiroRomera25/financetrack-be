package project.financetrack.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import project.financetrack.controllers.genericSegregation.basicCRUD.ControllerGetById;
import project.financetrack.controllers.genericSegregation.compositeUniqueAtt.ControllerGetAllByCompositeUniqueAtt;
import project.financetrack.dtos.project.ProjectDTOPost;
import project.financetrack.dtos.project.ProjectDTOPut;
import project.financetrack.entities.ProjectEntity;
import project.financetrack.security.jwt.JwtService;
import project.financetrack.services.AuthService;
import project.financetrack.services.ProjectService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects")
public class ProjectController
implements ControllerGetById<ProjectEntity, Long, ProjectEntity, ProjectService>,
        ControllerGetAllByCompositeUniqueAtt<ProjectEntity, Long, ProjectEntity, ProjectService>
{

    private final ProjectService projectService;

    private final AuthService authService;

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

    @DeleteMapping("/{projectId}")
    public ResponseEntity<ProjectEntity> delete(@PathVariable Long projectId, Authentication auth) {
        if (authService.canAccessProject(jwtService.extractId(auth), projectId)) {
            return ResponseEntity.ok(getService().delete(projectId));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectEntity> update(@PathVariable Long projectId, @RequestBody @Valid ProjectDTOPut dtoPut, Authentication auth) {
        if (authService.canAccessProject(jwtService.extractId(auth), projectId)) {
            return ResponseEntity.ok(getService().update(dtoPut, projectId));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}

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
import project.financetrack.controllers.genericSegregation.basicCRUD.ControllerCreate;
import project.financetrack.controllers.genericSegregation.basicCRUD.ControllerGetById;
import project.financetrack.controllers.genericSegregation.compositeUniqueAtt.ControllerGetByCompositeUniqueAtt;
import project.financetrack.dtos.category.CategoryDTO;
import project.financetrack.dtos.category.CategoryDTOPost;
import project.financetrack.dtos.category.CategoryDTOPut;
import project.financetrack.dtos.project.ProjectDTOPut;
import project.financetrack.entities.CategoryEntity;
import project.financetrack.entities.ProjectEntity;
import project.financetrack.security.jwt.JwtService;
import project.financetrack.services.AuthService;
import project.financetrack.services.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController
{

    private final CategoryService categoryService;

    private final AuthService authService;

    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<CategoryEntity> create(@RequestBody @Valid CategoryDTOPost dtoPost, Authentication auth) {
        if (authService.canAccessProject(jwtService.extractId(auth), dtoPost.getProjectId())) {
            return ResponseEntity.ok(categoryService.create(dtoPost));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/project/{projectId}")
    public List<CategoryDTO> getAllCategoriesByProjectId(@PathVariable Long projectId, Authentication auth) {
        if (authService.canAccessProject(jwtService.extractId(auth), projectId)) {
            return categoryService.getAllCategoriesByProjectId(projectId);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }


    @DeleteMapping("/project/{projectId}")
    public ResponseEntity<CategoryEntity> delete(@PathVariable Long projectId, Authentication auth) {
        if (authService.canAccessProject(jwtService.extractId(auth), projectId)) {
            return ResponseEntity.ok(categoryService.delete(projectId));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/project/{projectId}")
    public ResponseEntity<CategoryEntity> update(@PathVariable Long projectId, @RequestBody @Valid CategoryDTOPut dtoPut, Authentication auth) {
        if (authService.canAccessProject(jwtService.extractId(auth), projectId)) {
            return ResponseEntity.ok(categoryService.update(dtoPut, projectId));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}

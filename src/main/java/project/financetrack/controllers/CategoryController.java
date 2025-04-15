package project.financetrack.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.financetrack.controllers.genericSegregation.basicCRUD.ControllerCreate;
import project.financetrack.controllers.genericSegregation.basicCRUD.ControllerGetById;
import project.financetrack.controllers.genericSegregation.compositeUniqueAtt.ControllerGetByCompositeUniqueAtt;
import project.financetrack.dtos.category.CategoryDTOPost;
import project.financetrack.entities.CategoryEntity;
import project.financetrack.services.CategoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController implements
        ControllerCreate<CategoryEntity, Long, CategoryEntity, CategoryDTOPost, CategoryService>,
        ControllerGetById<CategoryEntity, Long, CategoryEntity, CategoryService>,
        ControllerGetByCompositeUniqueAtt<CategoryEntity, Long, CategoryEntity, CategoryService>
{

    private final CategoryService categoryService;

    @Override
    public CategoryService getService() {
        return categoryService;
    }
}

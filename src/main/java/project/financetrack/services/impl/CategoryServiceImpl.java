package project.financetrack.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.financetrack.dtos.category.CategoryDTO;
import project.financetrack.dtos.category.CategoryDTOPost;
import project.financetrack.entities.CategoryEntity;
import project.financetrack.entities.ProjectEntity;
import project.financetrack.entities.UserEntity;
import project.financetrack.repositories.CategoryRepository;
import project.financetrack.repositories.GenericRepository;
import project.financetrack.repositories.specs.SpecificationBuilder;
import project.financetrack.services.CategoryService;
import project.financetrack.services.ProjectService;
import project.financetrack.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;

    private final CategoryRepository categoryRepository;

    private final ProjectService projectService;

    private final UserService userService;

    private final SpecificationBuilder<CategoryEntity> specificationBuilder;

    @Override
    public CategoryEntity create(CategoryDTOPost dtoPost) {
        Map att = Map.of(
                "name", dtoPost.getName(),
                "project.id", dtoPost.getProjectId()
        );

        if (CategoryService.super.getOptionalByCompositeUniqueFields(att).isPresent()) {
            throw new IllegalArgumentException("Category name duplicate.");
        }

        ProjectEntity project = this.projectService.getById(dtoPost.getProjectId());
        UserEntity user = project.getUser();

        if (!user.getPremium() && project.getCategories().size() >= 10) {
            throw new IllegalArgumentException("To have more categories on this project buy premium");
        }

        CategoryEntity category = CategoryEntity.builder()
                .project(project)
                .name(dtoPost.getName())
                .color(dtoPost.getColor())
                .build();

        return CategoryService.super.createWithEntity(category);
    }

    @Override
    public void importCategoriesToProject(Long projectSourceId, Long projectTargetId, Long userId) {

    }

    @Override
    public CategoryEntity delete(Long id) {
        CategoryEntity category = this.getById(id);
        category.getTransactions()
                .forEach(transactionEntity -> transactionEntity.setIsActive(false));
        return CategoryService.super.delete(id);
    }

    @Override
    public List<CategoryDTO> getAllCategoriesByProjectId(Long projectId) {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        List<CategoryEntity> categories = CategoryService.super.getAllByUniqueFields("project.id", projectId);
        for (CategoryEntity category : categories) {
            categoryDTOS.add(
                CategoryDTO.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .color(category.getColor())
                    .transactionCount(category.getTransactions().size())
                    .build()
            );
        }
        return categoryDTOS;
    }

    @Override
    public ModelMapper getMapper() {
        return modelMapper;
    }

    @Override
    public GenericRepository<CategoryEntity, Long> getRepository() {
        return categoryRepository;
    }

    @Override
    public Class<CategoryEntity> entityClass() {
        return CategoryEntity.class;
    }

    @Override
    public Class<CategoryEntity> modelClass() {
        return CategoryEntity.class;
    }

    @Override
    public SpecificationBuilder<CategoryEntity> specificationBuilder() {
        return specificationBuilder;
    }
}

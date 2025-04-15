package project.financetrack.services.impl;

import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import project.financetrack.entities.CategoryEntity;
import project.financetrack.entities.UserEntity;
import project.financetrack.repositories.CategoryRepository;
import project.financetrack.repositories.GenericRepository;
import project.financetrack.repositories.UserRepository;
import project.financetrack.repositories.specs.GenericSpecification;
import project.financetrack.repositories.specs.SpecificationBuilder;
import project.financetrack.services.CategoryService;

@Server
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;

    private final CategoryRepository categoryRepository;

    private final SpecificationBuilder<CategoryEntity> specificationBuilder;

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

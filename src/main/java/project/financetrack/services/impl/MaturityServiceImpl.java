package project.financetrack.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.financetrack.entities.MaturityEntity;
import project.financetrack.entities.ProjectEntity;
import project.financetrack.repositories.GenericRepository;
import project.financetrack.repositories.MaturityRepository;
import project.financetrack.repositories.ProjectRepository;
import project.financetrack.repositories.specs.SpecificationBuilder;
import project.financetrack.services.MaturityService;
import project.financetrack.services.UserService;

@Service
@RequiredArgsConstructor
public class MaturityServiceImpl implements MaturityService {

    private final ModelMapper modelMapper;

    private final MaturityRepository maturityRepository;

    private final SpecificationBuilder<MaturityEntity> specificationBuilder;

    @Override
    public ModelMapper getMapper() {
        return modelMapper;
    }

    @Override
    public GenericRepository<MaturityEntity, Long> getRepository() {
        return maturityRepository;
    }

    @Override
    public SpecificationBuilder<MaturityEntity> specificationBuilder() {
        return specificationBuilder;
    }

    @Override
    public Class<MaturityEntity> entityClass() {
        return MaturityEntity.class;
    }

    @Override
    public Class<MaturityEntity> modelClass() {
        return MaturityEntity.class;
    }
}

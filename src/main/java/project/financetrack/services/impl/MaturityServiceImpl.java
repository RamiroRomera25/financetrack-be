package project.financetrack.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.financetrack.dtos.maturity.MaturityDTOPost;
import project.financetrack.entities.MaturityEntity;
import project.financetrack.enums.MaturityState;
import project.financetrack.repositories.GenericRepository;
import project.financetrack.repositories.MaturityRepository;
import project.financetrack.repositories.specs.SpecificationBuilder;
import project.financetrack.services.MaturityService;

@Service
@RequiredArgsConstructor
public class MaturityServiceImpl implements MaturityService {

    private final ModelMapper modelMapper;

    private final MaturityRepository maturityRepository;

    private final SpecificationBuilder<MaturityEntity> specificationBuilder;

    @Override
    public MaturityEntity create(MaturityDTOPost dtoPost) {
        MaturityEntity maturity = MaturityEntity.builder()
                .state(MaturityState.ON_WAIT)
                .quantity(dtoPost.getQuantity())
                .endDate(dtoPost.getEndDate())
                .build();

        return MaturityService.super.createWithEntity(maturity);
    }

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

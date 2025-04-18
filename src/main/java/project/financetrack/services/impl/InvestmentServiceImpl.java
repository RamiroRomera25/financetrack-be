package project.financetrack.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.financetrack.dtos.investment.InvestmentDTO;
import project.financetrack.dtos.investment.InvestmentDTOPost;
import project.financetrack.entities.InvestmentEntity;
import project.financetrack.repositories.GenericRepository;
import project.financetrack.repositories.InvestmentRepository;
import project.financetrack.repositories.specs.SpecificationBuilder;
import project.financetrack.services.InvestmentService;
import project.financetrack.services.ProjectService;

@Service
@RequiredArgsConstructor
public class InvestmentServiceImpl implements InvestmentService {

    private final ModelMapper modelMapper;

    private final InvestmentRepository investmentRepository;

    private final ProjectService projectService;

    private final SpecificationBuilder<InvestmentEntity> specificationBuilder;

    @Override
    public InvestmentDTO create(InvestmentDTOPost dtoPost) {
        // TODO: Validar contra YFinance que exista.

        InvestmentEntity investment = InvestmentEntity.builder()
                .project(projectService.getById(dtoPost.getProjectId()))
                .quantity(dtoPost.getQuantity())
                .tickerSymbol(dtoPost.getTickerSymbol())
                .build();

        return InvestmentService.super.createWithEntity(investment);
    }

    @Override
    public ModelMapper getMapper() {
        return modelMapper;
    }

    @Override
    public GenericRepository<InvestmentEntity, Long> getRepository() {
        return investmentRepository;
    }

    @Override
    public SpecificationBuilder<InvestmentEntity> specificationBuilder() {
        return specificationBuilder;
    }

    @Override
    public Class<InvestmentEntity> entityClass() {
        return InvestmentEntity.class;
    }

    @Override
    public Class<InvestmentDTO> modelClass() {
        return InvestmentDTO.class;
    }
}

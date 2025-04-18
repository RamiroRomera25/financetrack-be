package project.financetrack.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.financetrack.dtos.investment.InvestmentDTO;
import project.financetrack.dtos.investment.InvestmentDTOPost;
import project.financetrack.dtos.investment.InvestmentDTOPut;
import project.financetrack.entities.InvestmentEntity;
import project.financetrack.repositories.GenericRepository;
import project.financetrack.repositories.InvestmentRepository;
import project.financetrack.repositories.specs.SpecificationBuilder;
import project.financetrack.services.InvestmentService;
import project.financetrack.services.ProjectService;
import project.financetrack.services.YFinanceService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvestmentServiceImpl implements InvestmentService {

    private final ModelMapper modelMapper;

    private final InvestmentRepository investmentRepository;

    private final ProjectService projectService;

    private final YFinanceService yFinanceService;

    private final SpecificationBuilder<InvestmentEntity> specificationBuilder;

    @Override
    public InvestmentDTO create(InvestmentDTOPost dtoPost) throws IOException {
        InvestmentDTO investmentToReturn = this.yFinanceService.validateAndGetSymbolData(dtoPost.getTickerSymbol(), dtoPost.getQuantity());

        InvestmentEntity investmentEntity = InvestmentEntity.builder()
                .project(projectService.getById(dtoPost.getProjectId()))
                .quantity(dtoPost.getQuantity())
                .tickerSymbol(dtoPost.getTickerSymbol())
                .build();

        InvestmentEntity investmentSaved = this.investmentRepository.save(investmentEntity);

        investmentToReturn.setId(investmentSaved.getId());
        return investmentToReturn;
    }

    @Override
    public List<InvestmentDTO> getAllModelByUniqueFields(String uniqueField, Object value) throws IOException {
        List<InvestmentDTO> investments =
        this.investmentRepository.findAll(specificationBuilder
                        .uniqueValue(uniqueField, value)
                        .build())
                .stream()
                .map((entity) -> getMapper().map(entity, modelClass()))
                .collect(Collectors.toList());

        return this.yFinanceService.completeInvestmentData(investments);
    }

    @Override
    public InvestmentDTO update(InvestmentDTOPut dtoPut, Long id) throws IOException {
        InvestmentEntity entity = InvestmentService.super.getById(id);
        modelMapper.map(dtoPut, entity);
        InvestmentEntity investmentSaved = this.investmentRepository.save(entity);

        InvestmentDTO investmentToReturn = this.yFinanceService.validateAndGetSymbolData(
            investmentSaved.getTickerSymbol(), investmentSaved.getQuantity()
        );
        investmentToReturn.setId(investmentSaved.getId());

        return investmentToReturn;
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
    public Class<InvestmentDTO> modelClass() {
        return InvestmentDTO.class;
    }
}

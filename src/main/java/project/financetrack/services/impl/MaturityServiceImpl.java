package project.financetrack.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import project.financetrack.dtos.maturity.MaturityDTOPost;
import project.financetrack.entities.MaturityEntity;
import project.financetrack.enums.MaturityState;
import project.financetrack.repositories.GenericRepository;
import project.financetrack.repositories.MaturityRepository;
import project.financetrack.repositories.specs.SpecificationBuilder;
import project.financetrack.services.EmailService;
import project.financetrack.services.MaturityService;
import project.financetrack.services.ProjectService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MaturityServiceImpl implements MaturityService {

    private final ModelMapper modelMapper;

    private final MaturityRepository maturityRepository;

    private final SpecificationBuilder<MaturityEntity> specificationBuilder;

    private final EmailService emailService;

    private final ProjectService projectService;

    @Override
    public MaturityEntity create(MaturityDTOPost dtoPost) {
        MaturityEntity maturity = MaturityEntity.builder()
                .project(projectService.getById(dtoPost.getProjectId()))
                .state(MaturityState.ON_WAIT)
                .quantity(dtoPost.getQuantity())
                .endDate(dtoPost.getEndDate())
                .build();

        return MaturityService.super.createWithEntity(maturity);
    }

    @Scheduled(cron = "0 0 6 * * ?")
    @Override
    public void sendNotificationOnMaturities() {
        List<MaturityEntity> maturities = MaturityService.super.getAllModelByCompositeUniqueFields(
            Map.of(
            "endDate", LocalDate.now().plusDays(10L),
            "state", MaturityState.ON_WAIT
            )
        );

        for (MaturityEntity maturity : maturities) {
            maturity.setState(MaturityState.NOTIFICATED);
            emailService.maturityEmail(maturity, maturity.getState());
        }

        this.maturityRepository.saveAll(maturities);
    }

    @Scheduled(cron = "0 0 23 * * ?")
    @Override
    public void setMaturityStateLate() {
        List<MaturityEntity> maturities = MaturityService.super.getAllModelByCompositeUniqueFields(
            Map.of(
            "endDate", LocalDate.now(),
            "state", MaturityState.NOTIFICATED
            )
        );

        for (MaturityEntity maturity : maturities) {
            maturity.setState(MaturityState.LATE);
            emailService.maturityEmail(maturity, maturity.getState());
        }

        this.maturityRepository.saveAll(maturities);
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

package project.financetrack.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import project.financetrack.dtos.reminder.ReminderDTOPost;
import project.financetrack.entities.ReminderEntity;
import project.financetrack.repositories.GenericRepository;
import project.financetrack.repositories.ReminderRepository;
import project.financetrack.repositories.specs.SpecificationBuilder;
import project.financetrack.services.EmailService;
import project.financetrack.services.ProjectService;
import project.financetrack.services.ReminderService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReminderServiceImpl implements ReminderService {

    private final ModelMapper modelMapper;

    private final ReminderRepository goalRepository;

    private final ProjectService projectService;

    private final EmailService emailService;

    private final SpecificationBuilder<ReminderEntity> specificationBuilder;

    @Override
    public ReminderEntity create(ReminderDTOPost dtoPost) {
        ReminderEntity reminder = ReminderEntity.builder()
                .project(projectService.getById(dtoPost.getProjectId()))
                .reminderDate(dtoPost.getReminderDate())
                .subject(dtoPost.getSubject())
                .build();

        return ReminderService.super.createWithEntity(reminder);
    }

    @Scheduled(cron = "0 0 6 * * ?")
    public void sendReminders() {
        List<ReminderEntity> reminders = ReminderService.super.getAllModelByCompositeUniqueFields(
            Map.of(
                    "reminderDate", LocalDate.now(),
                    "isActive", true
            )
        );

        for (ReminderEntity reminder : reminders) {
            emailService.reminderEmail(reminder);
        }
    }

    @Override
    public ModelMapper getMapper() {
        return modelMapper;
    }

    @Override
    public GenericRepository<ReminderEntity, Long> getRepository() {
        return goalRepository;
    }

    @Override
    public Class<ReminderEntity> entityClass() {
        return ReminderEntity.class;
    }

    @Override
    public Class<ReminderEntity> modelClass() {
        return ReminderEntity.class;
    }

    @Override
    public SpecificationBuilder<ReminderEntity> specificationBuilder() {
        return specificationBuilder;
    }
}

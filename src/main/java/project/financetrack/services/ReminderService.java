package project.financetrack.services;

import org.springframework.stereotype.Service;
import project.financetrack.dtos.goal.GoalDTOFilter;
import project.financetrack.dtos.goal.GoalDTOPost;
import project.financetrack.dtos.goal.GoalDTOPut;
import project.financetrack.dtos.reminder.ReminderDTOPost;
import project.financetrack.dtos.reminder.ReminderDTOPut;
import project.financetrack.entities.ReminderEntity;
import project.financetrack.repositories.specs.SpecificationBuilder;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceCreate;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceGetById;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceSoftDelete;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceUpdate;
import project.financetrack.services.genericSegregation.filters.ServiceGetAllListFilter;
import project.financetrack.services.genericSegregation.uniqueAtt.ServiceGetAllByUniqueAtt;

@Service
public interface ReminderService extends
        ServiceCreate<ReminderEntity, Long, ReminderEntity, ReminderDTOPost>,
        ServiceGetById<ReminderEntity, Long, ReminderEntity>,
        ServiceSoftDelete<ReminderEntity, Long, ReminderEntity>,
        ServiceUpdate<ReminderEntity, Long, ReminderEntity, ReminderDTOPut>,
        ServiceGetAllByUniqueAtt<ReminderEntity, Long, ReminderEntity>
{
}

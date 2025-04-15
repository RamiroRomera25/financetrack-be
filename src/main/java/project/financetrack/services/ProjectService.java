package project.financetrack.services;

import org.springframework.stereotype.Service;
import project.financetrack.dtos.project.ProjectDTOPost;
import project.financetrack.entities.ProjectEntity;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceCreate;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceUpdate;
import project.financetrack.services.genericSegregation.compositeUniqueAtt.ServiceGetByCompositeUniqueAtt;
import project.financetrack.services.genericSegregation.uniqueAtt.ServiceGetByUniqueAtt;

@Service
public interface ProjectService extends
        ServiceCreate<ProjectEntity, Long, ProjectEntity, ProjectDTOPost>,
        ServiceGetByCompositeUniqueAtt<ProjectEntity, Long, ProjectEntity>
{
    void importCategoriesToProject(Long projectSourceId, Long projectTargetId, Long userId);
}

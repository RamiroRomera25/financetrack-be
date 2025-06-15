package project.financetrack.services;

import org.springframework.stereotype.Service;
import project.financetrack.dtos.project.ProjectDTOPost;
import project.financetrack.dtos.project.ProjectDTOPut;
import project.financetrack.entities.ProjectEntity;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceCreate;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceGetById;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceSoftDelete;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceUpdate;
import project.financetrack.services.genericSegregation.compositeUniqueAtt.ServiceGetAllByCompositeUniqueAtt;
import project.financetrack.services.genericSegregation.compositeUniqueAtt.ServiceGetByCompositeUniqueAtt;
import project.financetrack.services.genericSegregation.uniqueAtt.ServiceGetAllByUniqueAtt;
import project.financetrack.services.genericSegregation.uniqueAtt.ServiceGetByUniqueAtt;

import java.util.List;

@Service
public interface ProjectService extends
        ServiceCreate<ProjectEntity, Long, ProjectEntity, ProjectDTOPost>,
        ServiceGetByCompositeUniqueAtt<ProjectEntity, Long, ProjectEntity>,
        ServiceGetById<ProjectEntity, Long, ProjectEntity>,
        ServiceGetByUniqueAtt<ProjectEntity, Long, ProjectEntity>,
        ServiceGetAllByUniqueAtt<ProjectEntity, Long, ProjectEntity>,
        ServiceSoftDelete<ProjectEntity, Long, ProjectEntity>,
        ServiceUpdate<ProjectEntity, Long, ProjectEntity, ProjectDTOPut>,
        ServiceGetAllByCompositeUniqueAtt<ProjectEntity, Long, ProjectEntity>
{
    boolean existsProjectWithName(String projectName, Long userId);
}

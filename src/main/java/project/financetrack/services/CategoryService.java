package project.financetrack.services;

import org.springframework.stereotype.Service;
import project.financetrack.dtos.project.ProjectDTOPost;
import project.financetrack.entities.CategoryEntity;
import project.financetrack.entities.ProjectEntity;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceCreate;
import project.financetrack.services.genericSegregation.compositeUniqueAtt.ServiceGetByCompositeUniqueAtt;

@Service
public interface CategoryService extends
        ServiceCreate<CategoryEntity, Long, CategoryEntity, ProjectDTOPost>,
        ServiceGetByCompositeUniqueAtt<CategoryEntity, Long, CategoryEntity>
{
}

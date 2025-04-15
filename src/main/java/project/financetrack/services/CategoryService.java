package project.financetrack.services;

import org.springframework.stereotype.Service;
import project.financetrack.dtos.category.CategoryDTOPost;
import project.financetrack.dtos.project.ProjectDTOPost;
import project.financetrack.entities.CategoryEntity;
import project.financetrack.entities.ProjectEntity;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceCreate;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceGetById;
import project.financetrack.services.genericSegregation.compositeUniqueAtt.ServiceGetByCompositeUniqueAtt;

@Service
public interface CategoryService extends
        ServiceCreate<CategoryEntity, Long, CategoryEntity, CategoryDTOPost>,
        ServiceGetByCompositeUniqueAtt<CategoryEntity, Long, CategoryEntity>,
        ServiceGetById<CategoryEntity, Long, CategoryEntity>
{
    void importCategoriesToProject(Long projectSourceId, Long projectTargetId, Long userId);
}

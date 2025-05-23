package project.financetrack.services;

import org.springframework.stereotype.Service;
import project.financetrack.dtos.category.CategoryDTO;
import project.financetrack.dtos.category.CategoryDTOPost;
import project.financetrack.dtos.category.CategoryDTOPut;
import project.financetrack.entities.CategoryEntity;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceCreate;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceGetById;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceSoftDelete;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceUpdate;
import project.financetrack.services.genericSegregation.compositeUniqueAtt.ServiceGetByCompositeUniqueAtt;
import project.financetrack.services.genericSegregation.uniqueAtt.ServiceGetAllByUniqueAtt;

import java.util.List;

@Service
public interface CategoryService extends
        ServiceCreate<CategoryEntity, Long, CategoryEntity, CategoryDTOPost>,
        ServiceGetByCompositeUniqueAtt<CategoryEntity, Long, CategoryEntity>,
        ServiceGetAllByUniqueAtt<CategoryEntity, Long, CategoryEntity>,
        ServiceGetById<CategoryEntity, Long, CategoryEntity>,
        ServiceSoftDelete<CategoryEntity, Long, CategoryEntity>,
        ServiceUpdate<CategoryEntity, Long, CategoryEntity, CategoryDTOPut>
{
    void importCategoriesToProject(Long projectSourceId, Long projectTargetId, Long userId);

    List<CategoryDTO> getAllCategoriesByProjectId(Long projectId);
}

package project.financetrack.services.genericSegregation.compositeUniqueAtt;

import org.modelmapper.ModelMapper;
import project.financetrack.entities.base.BaseEntity;
import project.financetrack.repositories.GenericRepository;

import java.util.Map;

public interface ServiceSoftDeleteByCompositeUniqueAtt<E extends BaseEntity, I, M> extends ServiceGetByCompositeUniqueAtt<E, I, M> {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<M> modelClass();

    default M delete(Map<String, Object> uniqueFiels) {
        return changeActiveStatus(uniqueFiels, false);
    }

    default M reactivate(Map<String, Object> uniqueFiels) {
        return changeActiveStatus(uniqueFiels, true);
    }

    private M changeActiveStatus(Map<String, Object> uniqueFiels, boolean isActive) {
        E entity = this.getByCompositeUniqueFields(uniqueFiels);
        entity.setIsActive(isActive);
        return getMapper().map(getRepository().save(entity), modelClass());
    }
}

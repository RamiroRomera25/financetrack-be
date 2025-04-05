package project.financetrack.services.genericSegregation.uniqueAtt;

import org.modelmapper.ModelMapper;
import project.financetrack.entities.base.BaseEntity;
import project.financetrack.repositories.GenericRepository;

public interface ServiceSoftDeleteByUniqueAtt<E extends BaseEntity, I, M> extends ServiceGetByUniqueAtt<E, I, M> {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<M> modelClass();

    default M delete(String field, Object value) {
        return changeActiveStatus(field, value, false);
    }

    default M reactivate(String field, Object value) {
        return changeActiveStatus(field, value, true);
    }

    private M changeActiveStatus(String field, Object value, boolean isActive) {
        E entity = this.getByUniqueField(field, value);
        entity.setIsActive(isActive);
        return getMapper().map(getRepository().save(entity), modelClass());
    }
}

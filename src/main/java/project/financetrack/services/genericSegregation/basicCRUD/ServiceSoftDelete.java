package project.financetrack.services.genericSegregation.basicCRUD;

import org.modelmapper.ModelMapper;
import project.financetrack.entities.base.BaseEntity;
import project.financetrack.repositories.GenericRepository;

public interface ServiceSoftDelete<E extends BaseEntity, I, M> extends ServiceGetById<E, I, M> {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<M> modelClass();

    default M delete(I id) {
        return changeActiveStatus(id, false);
    }

    default M reactivate(I id) {
        return changeActiveStatus(id, true);
    }

    private M changeActiveStatus(I id, boolean isActive) {
        E entity = this.getById(id);
        entity.setIsActive(isActive);
        return getMapper().map(getRepository().save(entity), modelClass());
    }
}

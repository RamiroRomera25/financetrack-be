package project.financetrack.services.genericSegregation.basicCRUD;

import org.modelmapper.ModelMapper;
import project.financetrack.repositories.GenericRepository;


public interface ServiceCreate<E, I, M, DTOPOST> {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<E> entityClass();

    Class<M> modelClass();

    default M create(DTOPOST dtoPost) {
        E entityToSave = getMapper().map(dtoPost, entityClass());
        return getMapper().map(getRepository().save(entityToSave), modelClass());
    }
}

package project.financetrack.services.genericSegregation.uniqueAtt;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import project.financetrack.repositories.GenericRepository;
import project.financetrack.repositories.specs.SpecificationBuilder;

import java.util.Optional;

public interface ServiceGetByUniqueAtt<E, I, M> {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<E> entityClass();

    Class<M> modelClass();

    SpecificationBuilder<E> specificationBuilder();

    default E getByUniqueField(String uniqueField, Object value) {
        return getRepository().findOne(specificationBuilder().uniqueValue(uniqueField, value).build())
            .orElseThrow(() -> new EntityNotFoundException(
                entityClass().getSimpleName() + " not found with " + uniqueField + ": " + value));
    }

    default Optional<E> getOptionalByUniqueField(String uniqueField, Object value) {
        return getRepository().findOne(specificationBuilder().uniqueValue(uniqueField, value).build());
    }

    default M getModelByUniqueField(String uniqueField, Object value) {
        return getRepository().findOne(specificationBuilder().uniqueValue(uniqueField, value).build())
            .map((entity) -> getMapper().map(entity, modelClass()))
            .orElseThrow(() -> new EntityNotFoundException(
                    entityClass().getSimpleName() + " not found with " + uniqueField + ": " + value));
    }
}

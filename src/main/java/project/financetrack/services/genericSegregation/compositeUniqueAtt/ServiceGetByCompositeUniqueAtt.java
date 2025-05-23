package project.financetrack.services.genericSegregation.compositeUniqueAtt;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import project.financetrack.repositories.GenericRepository;
import project.financetrack.repositories.specs.SpecificationBuilder;

import java.util.Map;
import java.util.Optional;

public interface ServiceGetByCompositeUniqueAtt<E, I, M> {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<E> entityClass();

    Class<M> modelClass();

    SpecificationBuilder<E> specificationBuilder();

    default E getByCompositeUniqueFields(Map<String, Object> uniqueFields) {
        return getRepository().findOne(specificationBuilder().compositeUniqueValues(uniqueFields).build())
                .orElseThrow(() -> new EntityNotFoundException(buildErrorMessage(uniqueFields)));
    }

    default Optional<E> getOptionalByCompositeUniqueFields(Map<String, Object> uniqueFields) {
        return getRepository().findOne(specificationBuilder().compositeUniqueValues(uniqueFields).build());
    }

    default M getModelByCompositeUniqueFields(Map<String, Object> uniqueFields) {
        return getRepository().findOne(specificationBuilder().compositeUniqueValues(uniqueFields).build())
                .map((entity) -> getMapper().map(entity, modelClass()))
                .orElseThrow(() -> new EntityNotFoundException(buildErrorMessage(uniqueFields)));
    }

    private String buildErrorMessage(Map<String, Object> uniqueFields) {
        String fields = uniqueFields.entrySet().stream()
                .map(entry -> entry.getKey() + " = " + entry.getValue())
                .reduce((field1, field2) -> field1 + ", " + field2)
                .orElse("Unknown fields");

        return String.format("%s entity not found for %s.",
                entityClass().getSimpleName(), fields);
    }
}

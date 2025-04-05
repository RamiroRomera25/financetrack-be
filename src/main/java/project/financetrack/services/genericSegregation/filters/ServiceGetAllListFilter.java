package project.financetrack.services.genericSegregation.filters;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import project.financetrack.repositories.GenericRepository;
import project.financetrack.repositories.specs.SpecificationBuilder;
import project.financetrack.services.genericSegregation.ServiceMapper;

import java.util.List;

public interface ServiceGetAllListFilter<E, I, M, DTOFILTER> extends ServiceMapper {

    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    SpecificationBuilder<E> specificationBuilder();

    default List<M> getAll(DTOFILTER filter, Sort sort) {
        Specification<E> spec = specificationBuilder()
                .withDynamicFilter(this.getFilterMap(filter))
                .build();
        List<E> entityList = getRepository()
            .findAll(spec,
                    sort);
        if (!entityList.isEmpty()) {
            return getMapper().map(entityList, new TypeToken<List<M>>() {}.getType());
        } else {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No content retrieved.");
        }
    }
}

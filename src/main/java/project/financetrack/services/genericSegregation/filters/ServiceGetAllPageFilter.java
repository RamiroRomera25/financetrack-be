package project.financetrack.services.genericSegregation.filters;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import project.financetrack.repositories.GenericRepository;
import project.financetrack.repositories.specs.SpecificationBuilder;
import project.financetrack.services.genericSegregation.ServiceMapper;

public interface ServiceGetAllPageFilter<E, I, M, DTOFILTER> extends ServiceMapper {

    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    SpecificationBuilder<E> specificationBuilder();

    default Page<M> getAll(Pageable pageable, DTOFILTER filter) {
        Specification<E> spec = specificationBuilder()
                                .withDynamicFilter(this.getFilterMap(filter))
                                .build();
        Page<E> pageEntity = getRepository().findAll(spec, pageable);
        if (!pageEntity.isEmpty()) {
            return getMapper().map(pageEntity, new TypeToken<Page<M>>(){}.getType());
        } else {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No content retrieved.");
        }
    }

}

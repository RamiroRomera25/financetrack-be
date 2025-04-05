package project.financetrack.services.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.financetrack.dtos.dummy.DummyDtoFilter;
import project.financetrack.entities.DummyEntity;
import project.financetrack.models.DummyModel;
import project.financetrack.repositories.DummyRepository;
import project.financetrack.repositories.GenericRepository;
import project.financetrack.repositories.specs.GenericSpecification;
import project.financetrack.repositories.specs.SpecificationBuilder;
import project.financetrack.services.DummyService;

import java.util.List;

@Service
@Profile({"dev", "mysql", "postgre"})
public class DummyServiceImpl implements DummyService {

    //#region Autowired
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DummyRepository dummyRepository;

    @Autowired
    private GenericSpecification<DummyEntity> dummySpecification;

    @Autowired
    private SpecificationBuilder<DummyEntity> specificationBuilder;
    //#endregion

    //#region Override for Generic
    @Override
    public ModelMapper getMapper() {
        return modelMapper;
    }

    @Override
    public GenericRepository<DummyEntity, Long> getRepository() {
        return dummyRepository;
    }

    @Override
    public Class<DummyEntity> entityClass() {
        return DummyEntity.class;
    }

    @Override
    public Class<DummyModel> modelClass() {
        return DummyModel.class;
    }

    @Override
    public SpecificationBuilder<DummyEntity> specificationBuilder() {
        return specificationBuilder;
    }
    //#endregion

    @Override
    public List<DummyModel> dummyLike(DummyDtoFilter filter) {
        List<DummyEntity> entityList = getRepository().findAll(specificationBuilder
                                                    .withDynamicFilterLike(this.getFilterMap(filter))
                                                    .build());
        if (!entityList.isEmpty()) {
            return getMapper().map(entityList, new TypeToken<List<DummyModel>>() {}.getType());
        } else {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No content retrieved.");
        }
    }
}

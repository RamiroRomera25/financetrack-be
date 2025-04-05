package project.financetrack.services;

import org.springframework.stereotype.Service;
import project.financetrack.dtos.dummy.DummyDtoFilter;
import project.financetrack.dtos.dummy.DummyDtoPost;
import project.financetrack.dtos.dummy.DummyDtoPut;
import project.financetrack.entities.DummyEntity;
import project.financetrack.models.DummyModel;
import project.financetrack.services.genericSegregation.filters.ServiceGetAllListFilter;
import project.financetrack.services.genericSegregation.filters.ServiceGetAllPageFilter;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceCreate;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceCreateList;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceGetAllList;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceGetAllPage;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceGetById;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceSoftDelete;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceSoftDeleteList;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceUpdate;

import java.util.List;

@Service
public interface DummyService
extends ServiceGetAllPage<DummyEntity, Long, DummyModel>,
        ServiceGetAllList<DummyEntity, Long, DummyModel>,
        ServiceGetById<DummyEntity, Long, DummyModel>,
        ServiceCreate<DummyEntity, Long, DummyModel, DummyDtoPost>,
        ServiceUpdate<DummyEntity, Long, DummyModel, DummyDtoPut>,
        ServiceSoftDelete<DummyEntity, Long, DummyModel>,
        ServiceGetAllListFilter<DummyEntity, Long, DummyModel, DummyDtoFilter>,
        ServiceGetAllPageFilter<DummyEntity, Long, DummyModel, DummyDtoFilter>,
        ServiceCreateList<DummyEntity, Long, DummyModel, DummyDtoPost>,
        ServiceSoftDeleteList<DummyEntity, Long, DummyModel>
        {
    List<DummyModel> dummyLike(DummyDtoFilter filter);
}

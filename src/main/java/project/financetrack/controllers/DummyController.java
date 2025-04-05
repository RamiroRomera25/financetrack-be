package project.financetrack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.financetrack.controllers.genericSegregation.basicCRUD.ControllerCreate;
import project.financetrack.controllers.genericSegregation.basicCRUD.ControllerCreateList;
import project.financetrack.controllers.genericSegregation.basicCRUD.ControllerGetAllList;
import project.financetrack.controllers.genericSegregation.basicCRUD.ControllerGetAllPage;
import project.financetrack.controllers.genericSegregation.basicCRUD.ControllerGetById;
import project.financetrack.controllers.genericSegregation.basicCRUD.ControllerSoftDelete;
import project.financetrack.controllers.genericSegregation.basicCRUD.ControllerSoftDeleteList;
import project.financetrack.controllers.genericSegregation.basicCRUD.ControllerUpdate;
import project.financetrack.controllers.genericSegregation.filters.ControllerGetAllListFilter;
import project.financetrack.controllers.genericSegregation.filters.ControllerGetAllPageFilter;
import project.financetrack.dtos.dummy.DummyDtoFilter;
import project.financetrack.dtos.dummy.DummyDtoPost;
import project.financetrack.dtos.dummy.DummyDtoPut;
import project.financetrack.entities.DummyEntity;
import project.financetrack.models.DummyModel;
import project.financetrack.services.DummyService;

import java.util.List;

@RestController
@RequestMapping("/v5/dummy")
public class DummyController
implements ControllerGetById<DummyEntity, Long, DummyModel, DummyService>,
           ControllerGetAllList<DummyEntity, Long, DummyModel, DummyService>,
        ControllerGetAllPage<DummyEntity, Long, DummyModel, DummyService>,
        ControllerGetAllPageFilter<DummyEntity, Long, DummyModel, DummyDtoFilter, DummyService>,
        ControllerGetAllListFilter<DummyEntity, Long, DummyModel, DummyDtoFilter, DummyService>,
           ControllerSoftDelete<DummyEntity, Long, DummyModel, DummyService>,
           ControllerUpdate<DummyEntity, Long, DummyModel, DummyDtoPut, DummyService>,
        ControllerCreate<DummyEntity, Long, DummyModel, DummyDtoPost, DummyService>,
        ControllerCreateList<DummyEntity, Long, DummyModel, DummyDtoPost, DummyService>,
        ControllerSoftDeleteList<DummyEntity, Long, DummyModel, DummyService>
{

    @Autowired
    private DummyService dummyService;

    @GetMapping("/like")
    public ResponseEntity<List<DummyModel>> getDummiesLike(DummyDtoFilter filter) {
        return ResponseEntity.ok(dummyService.dummyLike(filter));
    }

    @Override
    public DummyService getService() {
        return dummyService;
    }
}

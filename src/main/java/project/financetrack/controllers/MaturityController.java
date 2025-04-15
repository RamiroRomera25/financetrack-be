package project.financetrack.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.financetrack.controllers.genericSegregation.basicCRUD.ControllerCreate;
import project.financetrack.dtos.maturity.MaturityDTOPost;
import project.financetrack.entities.MaturityEntity;
import project.financetrack.services.MaturityService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/maturities")
public class MaturityController implements
        ControllerCreate<MaturityEntity, Long, MaturityEntity, MaturityDTOPost, MaturityService>
{

    private final MaturityService maturityService;

    @Override
    public MaturityService getService() {
        return maturityService;
    }
}

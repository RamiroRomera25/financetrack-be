package project.financetrack.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.financetrack.controllers.genericSegregation.basicCRUD.ControllerCreate;
import project.financetrack.dtos.investment.InvestmentDTOFilter;
import project.financetrack.dtos.investment.InvestmentDTOPost;
import project.financetrack.entities.InvestmentEntity;
import project.financetrack.services.InvestmentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/investments")
public class InvestmentController implements
        ControllerCreate<InvestmentEntity, Long, InvestmentEntity, InvestmentDTOPost, InvestmentService>
{

    private final InvestmentService investmentService;

    @Override
    public InvestmentService getService() {
        return investmentService;
    }
}

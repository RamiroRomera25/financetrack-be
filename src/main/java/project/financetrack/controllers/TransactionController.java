package project.financetrack.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.financetrack.controllers.genericSegregation.basicCRUD.ControllerCreate;
import project.financetrack.dtos.transaction.TransactionDTOPost;
import project.financetrack.entities.TransactionEntity;
import project.financetrack.services.TransactionService;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceCreate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController implements
        ControllerCreate<TransactionEntity, Long, TransactionEntity, TransactionDTOPost, TransactionService>
{

    private final TransactionService transactionService;

    @Override
    public TransactionService getService() {
        return transactionService;
    }
}

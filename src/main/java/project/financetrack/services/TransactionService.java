package project.financetrack.services;

import project.financetrack.dtos.transaction.TransactionDTOFilter;
import project.financetrack.dtos.transaction.TransactionDTOPost;
import project.financetrack.entities.TransactionEntity;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceCreate;
import project.financetrack.services.genericSegregation.filters.ServiceGetAllListFilter;
import project.financetrack.services.genericSegregation.filters.ServiceGetAllPageFilter;

public interface TransactionService extends
        ServiceCreate<TransactionEntity, Long, TransactionEntity, TransactionDTOPost>,
        ServiceGetAllListFilter<TransactionEntity, Long, TransactionEntity, TransactionDTOFilter>,
        ServiceGetAllPageFilter<TransactionEntity, Long, TransactionEntity, TransactionDTOFilter>
{
}

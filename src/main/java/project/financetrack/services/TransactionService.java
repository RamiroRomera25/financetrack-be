package project.financetrack.services;

import project.financetrack.dtos.transaction.TransactionDTOFilter;
import project.financetrack.dtos.transaction.TransactionDTOPost;
import project.financetrack.dtos.transaction.TransactionDTOPut;
import project.financetrack.entities.TransactionEntity;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceCreate;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceSoftDelete;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceUpdate;
import project.financetrack.services.genericSegregation.filters.ServiceGetAllListFilter;
import project.financetrack.services.genericSegregation.filters.ServiceGetAllPageFilter;
import project.financetrack.services.genericSegregation.uniqueAtt.ServiceGetAllByUniqueAtt;

public interface TransactionService extends
        ServiceCreate<TransactionEntity, Long, TransactionEntity, TransactionDTOPost>,
        ServiceGetAllListFilter<TransactionEntity, Long, TransactionEntity, TransactionDTOFilter>,
        ServiceGetAllPageFilter<TransactionEntity, Long, TransactionEntity, TransactionDTOFilter>,
        ServiceUpdate<TransactionEntity, Long, TransactionEntity, TransactionDTOPut>,
        ServiceSoftDelete<TransactionEntity, Long, TransactionEntity>,
        ServiceGetAllByUniqueAtt<TransactionEntity, Long, TransactionEntity>
{
    TransactionEntity create(TransactionDTOPost dtoPost, Long projectId);
}

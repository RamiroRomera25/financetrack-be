package project.financetrack.services;

import org.springframework.stereotype.Service;
import project.financetrack.dtos.investment.InvestmentDTOPost;
import project.financetrack.dtos.investment.InvestmentDTOPut;
import project.financetrack.entities.InvestmentEntity;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceCreate;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceGetById;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceSoftDelete;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceUpdate;
import project.financetrack.services.genericSegregation.uniqueAtt.ServiceGetAllByUniqueAtt;

@Service
public interface InvestmentService extends
        ServiceCreate<InvestmentEntity, Long, InvestmentEntity, InvestmentDTOPost>,
        ServiceUpdate<InvestmentEntity, Long, InvestmentEntity, InvestmentDTOPut>,
        ServiceGetAllByUniqueAtt<InvestmentEntity, Long, InvestmentEntity>,
        ServiceGetById<InvestmentEntity, Long, InvestmentEntity>,
        ServiceSoftDelete<InvestmentEntity, Long, InvestmentEntity>
{
}

package project.financetrack.services;

import org.springframework.stereotype.Service;
import project.financetrack.dtos.investment.InvestmentDTO;
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
        ServiceCreate<InvestmentEntity, Long, InvestmentDTO, InvestmentDTOPost>,
        ServiceUpdate<InvestmentEntity, Long, InvestmentDTO, InvestmentDTOPut>,
        ServiceGetAllByUniqueAtt<InvestmentEntity, Long, InvestmentDTO>,
        ServiceGetById<InvestmentEntity, Long, InvestmentDTO>,
        ServiceSoftDelete<InvestmentEntity, Long, InvestmentDTO>
{
}

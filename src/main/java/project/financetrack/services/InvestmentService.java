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

import java.io.IOException;
import java.util.List;

@Service
public interface InvestmentService extends
        ServiceGetById<InvestmentEntity, Long, InvestmentDTO>,
        ServiceSoftDelete<InvestmentEntity, Long, InvestmentDTO>
{
    InvestmentDTO create(InvestmentDTOPost dtoPost) throws IOException;

    List<InvestmentDTO> getAllModelByUniqueFields(String uniqueField, Object value) throws IOException;

    InvestmentDTO update(InvestmentDTOPut dtoPut, Long id) throws IOException;
}

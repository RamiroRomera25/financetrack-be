package project.financetrack.services;

import org.springframework.stereotype.Service;
import project.financetrack.dtos.user.UserDTO;
import project.financetrack.entities.MaturityEntity;
import project.financetrack.enums.MaturityState;

/**
 * Service Interface to manage sending emails.
 */
@Service
public interface EmailService {
    void welcomeMail(UserDTO user);

    void maturityEmail(MaturityEntity maturity, MaturityState state);
}

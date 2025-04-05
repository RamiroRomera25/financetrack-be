package project.financetrack.services;

import org.springframework.stereotype.Service;
import project.financetrack.dtos.user.UserDTO;

/**
 * Service Interface to manage sending emails.
 */
@Service
public interface EmailService {
    void welcomeMail(UserDTO user);
}

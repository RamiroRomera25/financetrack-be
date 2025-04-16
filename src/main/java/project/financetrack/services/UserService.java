package project.financetrack.services;

import org.springframework.stereotype.Service;
import project.financetrack.dtos.user.UserDTO;
import project.financetrack.entities.UserEntity;
import project.financetrack.services.genericSegregation.basicCRUD.ServiceGetById;
import project.financetrack.services.genericSegregation.uniqueAtt.ServiceGetByUniqueAtt;

@Service
public interface UserService
extends
        ServiceGetByUniqueAtt<UserEntity, Long, UserDTO>,
        ServiceGetById<UserEntity, Long, UserDTO>
{
}

package project.financetrack.services.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.financetrack.dtos.user.UserDTO;
import project.financetrack.entities.UserEntity;
import project.financetrack.repositories.GenericRepository;
import project.financetrack.repositories.UserRepository;
import project.financetrack.repositories.specs.GenericSpecification;
import project.financetrack.repositories.specs.SpecificationBuilder;
import project.financetrack.services.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    private final GenericSpecification<UserEntity> userSpecification;

    private final SpecificationBuilder<UserEntity> specificationBuilder;

    @Override
    public ModelMapper getMapper() {
        return modelMapper;
    }

    @Override
    public GenericRepository<UserEntity, Long> getRepository() {
        return userRepository;
    }

    @Override
    public Class<UserEntity> entityClass() {
        return UserEntity.class;
    }

    @Override
    public Class<UserDTO> modelClass() {
        return UserDTO.class;
    }

    @Override
    public SpecificationBuilder<UserEntity> specificationBuilder() {
        return specificationBuilder;
    }
}

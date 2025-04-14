package project.financetrack.repositories;

import org.springframework.stereotype.Repository;
import project.financetrack.entities.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends GenericRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}

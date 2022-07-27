package pl.crc.gateway.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.crc.gateway.auth.model.UserAuth;

import java.util.Optional;

@Repository
public interface UserAuthJPARepository extends JpaRepository<UserAuth,Integer> {
   Optional<UserAuth> findByUsername(String username);
}

package pl.crc.gateway.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.crc.gateway.auth.model.UserAuth;

public interface UserAuthJPARepository extends JpaRepository<UserAuth,Integer> {
}

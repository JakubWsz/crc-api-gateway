package pl.crc.gateway.auth.service;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import pl.crc.gateway.auth.encoder.PBKDF2Encoder;
import pl.crc.gateway.auth.model.Role;
import pl.crc.gateway.auth.model.UserAuth;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements ApplicationRunner {
    private final PBKDF2Encoder pbkdf2Encoder;
    private final Map<String, UserAuth> users = new HashMap<>();

    public UserService(PBKDF2Encoder pbkdf2Encoder) {
        this.pbkdf2Encoder = pbkdf2Encoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        users.put("user", new UserAuth("user", "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=",
                true, List.of(Role.ROLE_REGULAR)));
        users.put("admin", new UserAuth("admin", "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=",
                true, List.of(Role.ROLE_ADMIN)));
    }

    public Mono<UserAuth> findByUsername(String username) {
        return Mono.justOrEmpty(users.get(username));
    }

    public UserAuth register(String username, String rawPassword) {
        String hashPassword = pbkdf2Encoder.encode(rawPassword);
        //todo validation username, passwrod isUsernameExists
        return users.put(username, new UserAuth(username, hashPassword, true, List.of(Role.ROLE_REGULAR)));
    }
}

package pl.crc.gateway.auth.service;

import org.springframework.stereotype.Service;
import pl.crc.gateway.auth.encoder.PBKDF2Encoder;
import pl.crc.gateway.auth.model.Role;
import pl.crc.gateway.auth.model.UserAuth;
import pl.crc.gateway.auth.validator.CredentialsValidator;
import pl.crc.gateway.db.UserAuthJPARepository;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserService {
    private final PBKDF2Encoder pbkdf2Encoder;
    private final UserAuthJPARepository userAuthJPARepository;

    public UserService(PBKDF2Encoder pbkdf2Encoder, UserAuthJPARepository userAuthJPARepository) {
        this.pbkdf2Encoder = pbkdf2Encoder;
        this.userAuthJPARepository = userAuthJPARepository;
    }

    public Mono<UserAuth> findByUsername(String username) {

        return Mono.justOrEmpty(userAuthJPARepository.findByUsername(username));
    }

    public UserAuth register(String username, String rawPassword) {
        String hashPassword = pbkdf2Encoder.encode(rawPassword);
        validateCredentials(username, rawPassword);
        return userAuthJPARepository.save(new UserAuth(username, hashPassword,
                true, List.of(Role.ROLE_REGULAR)));
    }

    private void validateCredentials(String username, String rawPassword) {
        CredentialsValidator.validateCredentials(username, rawPassword);
        userAuthJPARepository.findByUsername(username)
                .ifPresent(e -> {
                    throw new RuntimeException("podany login już istnieje");
                });
    }
}
package pl.crc.gateway.auth.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.crc.gateway.auth.encoder.PBKDF2Encoder;
import pl.crc.gateway.auth.model.RequestAuth;
import pl.crc.gateway.auth.model.ResponseAuth;
import pl.crc.gateway.auth.model.UserAuth;
import pl.crc.gateway.auth.service.UserService;
import pl.crc.gateway.auth.utils.JWTUtil;
import reactor.core.publisher.Mono;

@RestController
public class AuthenticationREST {
    private final JWTUtil jwtUtil;
    private final PBKDF2Encoder pbkdf2Encoder;
    private final UserService userService;

    public AuthenticationREST(JWTUtil jwtUtil, PBKDF2Encoder pbkdf2Encoder, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.pbkdf2Encoder = pbkdf2Encoder;
        this.userService = userService;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<ResponseAuth>> login(@RequestBody RequestAuth requestAuth) {
        return userService.findByUsername(requestAuth.getUsername())
                .filter(userAuth -> pbkdf2Encoder.encode(requestAuth.getPassword()).equals(userAuth.getPassword()))
                .map(userAuth -> ResponseEntity.ok(new ResponseAuth(jwtUtil.generateToken(userAuth))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<UserAuth>> register(@RequestBody RequestAuth requestAuth) {
        return Mono.just(ResponseEntity.ok(userService.register(requestAuth.getUsername(), requestAuth.getPassword())));
    }
}

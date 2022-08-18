package pl.crc.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.crc.gateway.auth.model.RequestAuth;
import pl.crc.gateway.auth.service.UserService;
import pl.crc.gateway.db.UserAuthJPARepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
//@WebFluxTest
class AuthenticationRESTTest {
    @Autowired
    private WebTestClient wtc;
//    @Autowired
//    private ObjectMapper mapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserAuthJPARepository userAuthJPARepository;

    private static final String REGISTER_ENDPOINT = "/auth/register";
    private static final String USERNAME = "JanKowalski";
    private static final String PASSWORD = "asdASD123@!#";

//    @Test
//    void createRegisterRequestShouldReturn201Status() throws Exception {
//        RequestAuth requestAuth = new RequestAuth(USERNAME, PASSWORD);
//
//        createRegisterRequest(requestAuth)
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }
//
//    private ResultActions createRegisterRequest(RequestAuth requestAuth) throws Exception {
//        return mvc.perform(MockMvcRequestBuilders.post(REGISTER_ENDPOINT)
//                .content(mapper.writeValueAsString(requestAuth))
//                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
//    }
}

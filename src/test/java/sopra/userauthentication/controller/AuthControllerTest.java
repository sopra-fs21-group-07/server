package sopra.userauthentication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.server.ResponseStatusException;
import sopra.userauthentication.dto.AuthenticationResponse;
import sopra.userauthentication.dto.LoginRequest;
import sopra.userauthentication.dto.RefreshTokenRequest;
import sopra.userauthentication.dto.RegisterRequest;
import sopra.userauthentication.model.User;
import sopra.userauthentication.service.AuthService;
import sopra.userauthentication.service.RefreshTokenService;
import sopra.userauthentication.service.UserService;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @MockBean
    private UserService userService;

    @MockBean
    private RefreshTokenService refreshTokenService;

    @Test
    void signup() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("max.muster@abc.ch");
        registerRequest.setUsername("max123");
        registerRequest.setPassword("password");
        registerRequest.setFirstName("Max");
        registerRequest.setLastName("Muster");
        registerRequest.setAge(20);
        registerRequest.setRegion("Zürich");

        MockHttpServletRequestBuilder postRequest = post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(registerRequest));

        MvcResult result = mockMvc.perform(postRequest).andReturn();

        verify(authService,times(1)).signup(Mockito.any());

        // test the http status of the response
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());

        // test the http method ()
        assertEquals(HttpMethod.POST.name(), result.getRequest().getMethod());
    }

    @Test
    void verifyAccount() throws Exception {
        String token = UUID.randomUUID().toString();
        MockHttpServletRequestBuilder postRequest = get("/api/auth/accountVerification/"+token)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(postRequest).andReturn();

        verify(authService,times(1)).verifyAccount(Mockito.anyString());

        // test the http status of the response
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        // test the http method ()
        assertEquals(HttpMethod.GET.name(), result.getRequest().getMethod());
    }

    @Test
    void login() throws Exception {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("password");
        loginRequest.setUsername("max123");

        MockHttpServletRequestBuilder postRequest = post("/api/auth/login/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loginRequest));

        MvcResult result = mockMvc.perform(postRequest).andReturn();

        verify(authService,times(1)).login(Mockito.any());

        // test the http status of the response
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());

        // test the http method ()
        assertEquals(HttpMethod.POST.name(), result.getRequest().getMethod());

    }

    @Test
    void refreshTokens() throws Exception {

        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        refreshTokenRequest.setRefreshToken("hfakfjfd23456542n24");
        refreshTokenRequest.setUsername("max123");

        MockHttpServletRequestBuilder postRequest = post("/api/auth/refresh/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(refreshTokenRequest));

        MvcResult result = mockMvc.perform(postRequest).andReturn();

        verify(authService,times(1)).refreshToken(Mockito.any());

        // test the http status of the response
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());

        // test the http method ()
        assertEquals(HttpMethod.POST.name(), result.getRequest().getMethod());
    }

    @Test
    void logout() throws Exception {
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        refreshTokenRequest.setRefreshToken("hfakfjfd23456542n24");
        refreshTokenRequest.setUsername("max123");

        MockHttpServletRequestBuilder postRequest = post("/api/auth/logout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(refreshTokenRequest));

        MvcResult result = mockMvc.perform(postRequest).andReturn();

        verify(refreshTokenService,times(1)).deleteRefreshToken(Mockito.anyString());

        // test the http status of the response
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        // test the http method ()
        assertEquals(HttpMethod.POST.name(), result.getRequest().getMethod());
    }

    @Test
    void getUser() throws Exception {
        User user = new User();
        user.setUserId(1L);
        user.setFirstName("Max");
        user.setLastName("Muster");
        user.setEmail("max.muster@abc.ch");
        user.setUsername("max123");
        user.setPassword("password");
        user.setAge(20);
        user.setRegion("Zürich");
        user.setEnabled(true);
        user.setCreated(Instant.now());

        given(userService.getUserByUsername(Mockito.anyString())).willReturn(user);

        MockHttpServletRequestBuilder getRequest = get("/profilePage/max123")
                .contentType(MediaType.APPLICATION_JSON);
        // then
        MvcResult result = mockMvc.perform(getRequest).andReturn();

        // test the http status of the response --> Because max123 didnt get a refreshToken
        assertEquals(HttpStatus.FORBIDDEN.value(), result.getResponse().getStatus());

        // test the http method ()
        assertEquals(HttpMethod.GET.name(), result.getRequest().getMethod());

        // test the content type of the response
        assertEquals(MediaType.APPLICATION_JSON.toString(), result.getRequest().getContentType());

    }

    private String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        }
        catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("The request body could not be created.%s", e.toString()));
        }
    }
}
package sopra.userauthentication.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import sopra.userauthentication.dto.RegisterRequest;
import sopra.userauthentication.model.RefreshToken;
import sopra.userauthentication.model.User;
import sopra.userauthentication.model.VerificationToken;
import sopra.userauthentication.repository.RefreshTokenRepository;
import sopra.userauthentication.repository.UserRepository;
import sopra.userauthentication.repository.VerificationTokenRepository;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private VerificationTokenRepository verificationTokenRepository;

    @Mock
    private MailService mailService;

    @Mock
    private User testUser;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        testUser = new User();
        testUser.setUserId(1L);
        testUser.setUsername("max123");
        testUser.setPassword("password");
        testUser.setEmail("max.muster@abc.ch");
        testUser.setEnabled(true);
        testUser.setCreated(Instant.now());
    }

    @Test
    void signup() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("max123");
        registerRequest.setPassword("password");
        registerRequest.setEmail("max.muster@abc.ch");
        registerRequest.setFirstName("Max");
        registerRequest.setLastName("Muster");
        registerRequest.setRegion("Zürich");
        registerRequest.setAge(23);

        authService.signup(registerRequest);

        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any());

        Mockito.verify(mailService, Mockito.times(1)).sendMail(Mockito.any());


    }

    @Test
    void getCurrentUser() {
        //authService.getCurrentUser();

        //Mockito.verify(userRepository, Mockito.times(1)).findByUsername(Mockito.anyString());
    }

    @Test
    void verifyAccount() {
        /*String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(testUser);
        verificationTokenRepository.save(verificationToken);


        authService.verifyAccount(token);

        Mockito.verify(verificationTokenRepository, Mockito.times(1)).findByToken(Mockito.anyString());*/

    }

    @Test
    void login() {
    }

    @Test
    void refreshToken() {
    }

    @Test
    void isLoggedIn() {
    }
}

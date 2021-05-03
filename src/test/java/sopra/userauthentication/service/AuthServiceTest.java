package sopra.userauthentication.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sopra.userauthentication.dto.AuthenticationResponse;
import sopra.userauthentication.dto.LoginRequest;
import sopra.userauthentication.dto.RefreshTokenRequest;
import sopra.userauthentication.dto.RegisterRequest;
import sopra.userauthentication.model.RefreshToken;
import sopra.userauthentication.model.User;
import sopra.userauthentication.model.VerificationToken;
import sopra.userauthentication.repository.RefreshTokenRepository;
import sopra.userauthentication.repository.UserRepository;
import sopra.userauthentication.repository.VerificationTokenRepository;
import sopra.userauthentication.security.JwtProvider;

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
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtProvider jwtProvider;

    @Mock
    private RefreshTokenService refreshTokenService;

    @Mock
    private RefreshToken refreshToken;


    @Mock
    private org.springframework.security.core.userdetails.User springUser;

    @Mock
    private org.springframework.security.core.context.SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContextHolder securityContextHolder;

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
        /*Mockito.when((org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal()).thenReturn(springUser);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        authService.getCurrentUser();

        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(Mockito.anyString());*/
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
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("max123");
        loginRequest.setPassword("password");

        Mockito.when(refreshTokenService.generateRefreshToken()).thenReturn(refreshToken);
        AuthenticationResponse response = authService.login(loginRequest);

        Mockito.verify(jwtProvider, Mockito.times(1)).generateToken(Mockito.any());
        Mockito.verify(authenticationManager, Mockito.times(1)).authenticate(Mockito.any());
    }

    @Test
    void refreshToken() {
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        refreshTokenRequest.setRefreshToken(UUID.randomUUID().toString());
        refreshTokenRequest.setUsername("max123");

        AuthenticationResponse response = authService.refreshToken(refreshTokenRequest);
        Mockito.verify(jwtProvider, Mockito.times(1)).generateTokenWithUserName(Mockito.anyString());
    }

    @Test
    void isLoggedIn() {
        /*SecurityContext securityContext = securityContextHolder.getContext();

        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        authService.isLoggedIn();*/
    }
}

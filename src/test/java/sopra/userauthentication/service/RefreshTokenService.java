package sopra.userauthentication.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import sopra.tour.repository.TourRepository;
import sopra.tour.service.TourService;
import sopra.userauthentication.model.RefreshToken;
import sopra.userauthentication.repository.RefreshTokenRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@WebAppConfiguration
@SpringBootTest
public class RefreshTokenService {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;


    @Autowired
    private sopra.userauthentication.service.RefreshTokenService refreshTokenService;

    @BeforeEach
    public void setup() {
        refreshTokenRepository.deleteAll();
    }

    @Test
    public void CheckRefreshTokenCreation(){
        refreshTokenService.generateRefreshToken();
    }

    @Test
    public void CheckRefreshTokenMessageException(){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());
        refreshTokenService.deleteRefreshToken(refreshToken.toString());
        assertEquals(refreshTokenRepository.findByToken(refreshToken.toString()), Optional.empty());
    }

}
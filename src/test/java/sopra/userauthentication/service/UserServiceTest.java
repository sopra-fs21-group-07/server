package sopra.userauthentication.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import sopra.userauthentication.model.User;
import sopra.userauthentication.repository.UserRepository;
import static org.mockito.BDDMockito.given;


import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // given
        testUser = new User();
        testUser.setUserId(1L);
        testUser.setUsername("max123");
        testUser.setPassword("password");
        testUser.setEmail("max.muster@abc.ch");
        testUser.setEnabled(true);
        testUser.setCreated(Instant.now());

        // when -> any object is being found in the UserRepository -> return the dummy testUser
        //Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(java.util.Optional.ofNullable(testUser));
    }

    @Test
    void getUserByUsername() {
        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(java.util.Optional.ofNullable(testUser));
        User getUser = userService.getUserByUsername("max123");

        Mockito.verify(userRepository, Mockito.times(2)).findByUsername(Mockito.anyString());

        assertEquals(testUser.getUsername(), getUser.getUsername());
        assertEquals(testUser.getUserId(), getUser.getUserId());
        assertEquals(testUser.getPassword(), getUser.getPassword());
        assertEquals(testUser.getEmail(), getUser.getEmail());
    }

    @Test
    void editUsername() {
        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(java.util.Optional.ofNullable(testUser));
        userService.editUsername("max123", "maxTest");

        Mockito.verify(userRepository, Mockito.times(3)).findByUsername(Mockito.anyString());
        Mockito.verify(userRepository, Mockito.times(1)).flush();

        assertEquals("maxTest", testUser.getUsername());

    }

    @Test
    void editFirstName() {
        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(java.util.Optional.ofNullable(testUser));
        userService.editFirstName("max123", "Test");

        Mockito.verify(userRepository, Mockito.times(2)).findByUsername(Mockito.anyString());
        Mockito.verify(userRepository, Mockito.times(1)).flush();

        assertEquals("Test", testUser.getFirstName());
    }

    @Test
    void editLastName() {
        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(java.util.Optional.ofNullable(testUser));
        userService.editLastName("max123", "Test");

        Mockito.verify(userRepository, Mockito.times(2)).findByUsername(Mockito.anyString());
        Mockito.verify(userRepository, Mockito.times(1)).flush();

        assertEquals("Test", testUser.getLastName());
    }

    @Test
    void editAge() {
        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(java.util.Optional.ofNullable(testUser));
        userService.editAge("max123", 69);

        Mockito.verify(userRepository, Mockito.times(2)).findByUsername(Mockito.anyString());
        Mockito.verify(userRepository, Mockito.times(1)).flush();

        assertEquals(69, testUser.getAge());
    }

    @Test
    void editRegion() {
        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(java.util.Optional.ofNullable(testUser));
        userService.editRegion("max123", "Lugano");

        Mockito.verify(userRepository, Mockito.times(2)).findByUsername(Mockito.anyString());
        Mockito.verify(userRepository, Mockito.times(1)).flush();

        assertEquals("Lugano", testUser.getRegion());
    }

    @Test
    void editPassword() {
        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(java.util.Optional.ofNullable(testUser));
        userService.editPassword("max123", "123456789");

        Mockito.verify(userRepository, Mockito.times(2)).findByUsername(Mockito.anyString());
        Mockito.verify(userRepository, Mockito.times(1)).flush();
    }

    @Test
    void MethodsFail() {
        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> userService.getUserByUsername("max"));
        assertThrows(ResponseStatusException.class, () -> userService.editUsername("max", "alex"));
        assertThrows(ResponseStatusException.class, () -> userService.editFirstName("max", "moritz"));
        assertThrows(ResponseStatusException.class, () -> userService.editLastName("max", "moritz"));
        assertThrows(ResponseStatusException.class, () -> userService.editPassword("max", "moritz"));
        assertThrows(ResponseStatusException.class, () -> userService.editAge("max", 99));
        assertThrows(ResponseStatusException.class, () -> userService.editRegion("max", "Genf"));
    }
}
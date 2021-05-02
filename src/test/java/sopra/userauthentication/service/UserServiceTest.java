package sopra.userauthentication.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
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
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(java.util.Optional.ofNullable(testUser));
    }

    @Test
    void getUserByUsername() {
        User getUser = userRepository.findByUsername("max123").get();

        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(Mockito.anyString());

        assertEquals(testUser.getUsername(), getUser.getUsername());
        assertEquals(testUser.getUserId(), getUser.getUserId());
        assertEquals(testUser.getPassword(), getUser.getPassword());
        assertEquals(testUser.getEmail(), getUser.getEmail());

    }

    @Test
    void editUsername() {
        User getUser = userRepository.findByUsername("max123").get();
        getUser.setUsername("maxTest");
        userRepository.flush();

        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(Mockito.anyString());
        //Mockito.verify(getUser, Mockito.times(1)).setUsername(Mockito.anyString());
        Mockito.verify(userRepository, Mockito.times(1)).flush();

        assertEquals("maxTest", getUser.getUsername());

    }

    @Test
    void editFirstName() {
        User getUser = userRepository.findByUsername("max123").get();
        getUser.setFirstName("Maxi");
        userRepository.flush();

        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(Mockito.anyString());
        Mockito.verify(userRepository, Mockito.times(1)).flush();

        assertEquals("Maxi", getUser.getFirstName());
    }

    @Test
    void editLastName() {
        User getUser = userRepository.findByUsername("max123").get();
        getUser.setLastName("Musterli");
        userRepository.flush();

        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(Mockito.anyString());
        Mockito.verify(userRepository, Mockito.times(1)).flush();

        assertEquals("Musterli", getUser.getLastName());
    }

    @Test
    void editAge() {
        User getUser = userRepository.findByUsername("max123").get();
        getUser.setAge(99);
        userRepository.flush();

        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(Mockito.anyString());
        Mockito.verify(userRepository, Mockito.times(1)).flush();

        assertEquals(99, getUser.getAge());
    }

    @Test
    void editRegion() {
        User getUser = userRepository.findByUsername("max123").get();
        getUser.setRegion("St. Gallen");
        userRepository.flush();

        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(Mockito.anyString());
        Mockito.verify(userRepository, Mockito.times(1)).flush();

        assertEquals("St. Gallen", getUser.getRegion());
    }

    @Test
    void editPassword() {
        User getUser = userRepository.findByUsername("max123").get();
        getUser.setPassword("pw123");
        userRepository.flush();

        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(Mockito.anyString());
        Mockito.verify(userRepository, Mockito.times(1)).flush();

        assertEquals("pw123", getUser.getPassword());
    }

    @Test
    void getUserByUsernameFail() {
        doThrow(new ResponseStatusException(HttpStatus.CONFLICT)).when(userRepository).findByUsername(Mockito.anyString());
    }

    @Test
    void editUsernameFail() {
        doThrow(new ResponseStatusException(HttpStatus.CONFLICT)).when(userRepository).findByUsername(Mockito.anyString());
    }
}
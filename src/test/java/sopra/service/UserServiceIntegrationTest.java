package sopra.service;

/*import sopra.appuser.AppUserService;
import sopra.appuser.AppUserStatus;
import sopra.appuser.AppUser;
import sopra.appuser.AppUserRepository;*/
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the AppUserResource REST resource.
 *
 * @see AppUserService
 */
@WebAppConfiguration
@SpringBootTest
class AppUserServiceIntegrationTest {

    /*@Qualifier("AppUserRepository")
    @Autowired
    private AppUserRepository AppUserRepository;

    @Autowired
    private AppUserService AppUserService;

    @BeforeEach
    public void setup() {
        AppUserRepository.deleteAll();
    }

    @Test
    public void createAppUser_validInputs_success() {
        // given
        assertNull(AppUserRepository.findByAppUsername("testAppUsername"));

        AppUser testAppUser = new AppUser();
        testAppUser.setName("testName");
        testAppUser.setAppUsername("testAppUsername");

        // when
        AppUser createdAppUser = AppUserService.createAppUser(testAppUser);

        // then
        assertEquals(testAppUser.getId(), createdAppUser.getId());
        assertEquals(testAppUser.getName(), createdAppUser.getName());
        assertEquals(testAppUser.getAppUsername(), createdAppUser.getAppUsername());
        assertNotNull(createdAppUser.getToken());
        assertEquals(AppUserStatus.OFFLINE, createdAppUser.getStatus());
    }

    @Test
    public void createAppUser_duplicateAppUsername_throwsException() {
        assertNull(AppUserRepository.findByAppUsername("testAppUsername"));

        AppUser testAppUser = new AppUser();
        testAppUser.setName("testName");
        testAppUser.setAppUsername("testAppUsername");
        AppUser createdAppUser = AppUserService.createAppUser(testAppUser);

        // attempt to create second AppUser with same AppUsername
        AppUser testAppUser2 = new AppUser();

        // change the name but forget about the AppUsername
        testAppUser2.setName("testName2");
        testAppUser2.setAppUsername("testAppUsername");

        // check that an error is thrown
        assertThrows(ResponseStatusException.class, () -> AppUserService.createAppUser(testAppUser2));
    }*/
}

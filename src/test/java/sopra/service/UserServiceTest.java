package sopra.service;

import sopra.appuser.AppUserService;
import sopra.appuser.AppUserStatus;
import sopra.appuser.AppUser;
import sopra.appuser.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

class AppUserServiceTest {

    @Mock
    private AppUserRepository AppUserRepository;

    @InjectMocks
    private AppUserService AppUserService;

    private AppUser testAppUser;

   /*@BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // given
        testAppUser = new AppUser();
        testAppUser.setId(1L);
        testAppUser.setName("testName");
        testAppUser.setAppUsername("testAppUsername");

        // when -> any object is being save in the AppUserRepository -> return the dummy testAppUser
        Mockito.when(AppUserRepository.save(Mockito.any())).thenReturn(testAppUser);
    }

    @Test
    public void createAppUser_validInputs_success() {
        // when -> any object is being save in the AppUserRepository -> return the dummy testAppUser
        AppUser createdAppUser = AppUserService.createAppUser(testAppUser);

        // then
        Mockito.verify(AppUserRepository, Mockito.times(1)).save(Mockito.any());

        assertEquals(testAppUser.getId(), createdAppUser.getId());
        assertEquals(testAppUser.getName(), createdAppUser.getName());
        assertEquals(testAppUser.getAppUsername(), createdAppUser.getAppUsername());
        assertNotNull(createdAppUser.getToken());
        assertEquals(AppUserStatus.OFFLINE, createdAppUser.getStatus());
    }

    @Test
    public void createAppUser_duplicateName_throwsException() {
        // given -> a first AppUser has already been created
        AppUserService.createAppUser(testAppUser);

        // when -> setup additional mocks for AppUserRepository
        Mockito.when(AppUserRepository.findByName(Mockito.any())).thenReturn(testAppUser);
        Mockito.when(AppUserRepository.findByAppUsername(Mockito.any())).thenReturn(null);

        // then -> attempt to create second AppUser with same AppUser -> check that an error is thrown
        assertThrows(ResponseStatusException.class, () -> AppUserService.createAppUser(testAppUser));
    }

    @Test
    public void createAppUser_duplicateInputs_throwsException() {
        // given -> a first AppUser has already been created
        AppUserService.createAppUser(testAppUser);

        // when -> setup additional mocks for AppUserRepository
        Mockito.when(AppUserRepository.findByName(Mockito.any())).thenReturn(testAppUser);
        Mockito.when(AppUserRepository.findByAppUsername(Mockito.any())).thenReturn(testAppUser);

        // then -> attempt to create second AppUser with same AppUser -> check that an error is thrown
        assertThrows(ResponseStatusException.class, () -> AppUserService.createAppUser(testAppUser));
    }
    */

}

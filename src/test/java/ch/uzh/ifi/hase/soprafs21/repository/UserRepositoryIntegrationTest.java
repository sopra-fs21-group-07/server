package sopra.repository;

import sopra.appAppUser.AppUserRepository;
import sopra.constant.AppUserStatus;
import sopra.appAppUser.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class AppUserRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppUserRepository AppUserRepository;

    @Test
    public void findByName_success() {
        // given
        AppUser AppUser = new AppUser();
        AppUser.setName("Firstname Lastname");
        AppUser.setAppUsername("firstname@lastname");
        AppUser.setStatus(AppUserStatus.OFFLINE);
        AppUser.setToken("1");

        entityManager.persist(AppUser);
        entityManager.flush();

        // when
        AppUser found = AppUserRepository.findByName(AppUser.getName());

        // then
        assertNotNull(found.getId());
        assertEquals(found.getName(), AppUser.getName());
        assertEquals(found.getAppUsername(), AppUser.getAppUsername());
        assertEquals(found.getToken(), AppUser.getToken());
        assertEquals(found.getStatus(), AppUser.getStatus());
    }
}

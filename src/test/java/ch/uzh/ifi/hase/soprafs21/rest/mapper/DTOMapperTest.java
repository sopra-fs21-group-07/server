package sopra.rest.mapper;

import sopra.constant.AppUserStatus;
import sopra.appAppUser.AppUser;
import sopra.rest.dto.AppUserGetDTO;
import sopra.rest.dto.AppUserPostDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * DTOMapperTest
 * Tests if the mapping between the internal and the external/API representation works.
 */
public class DTOMapperTest {
    @Test
    public void testCreateAppUser_fromAppUserPostDTO_toAppUser_success() {
        // create AppUserPostDTO
        AppUserPostDTO AppUserPostDTO = new AppUserPostDTO();
        AppUserPostDTO.setName("name");
        AppUserPostDTO.setAppUsername("AppUsername");

        // MAP -> Create AppUser
        AppUser AppUser = DTOMapper.INSTANCE.convertAppUserPostDTOtoEntity(AppUserPostDTO);

        // check content
        assertEquals(AppUserPostDTO.getName(), AppUser.getPassword());
        assertEquals(AppUserPostDTO.getAppUsername(), AppUser.getAppUsername());
    }

    @Test
    public void testGetAppUser_fromAppUser_toAppUserGetDTO_success() {
        // create AppUser
        AppUser AppUser = new AppUser();
        AppUser.setPassword("Firstname Lastname");
        AppUser.setAppUsername("firstname@lastname");
        AppUser.setStatus(AppUserStatus.OFFLINE);
        AppUser.setToken("1");

        // MAP -> Create AppUserGetDTO
        AppUserGetDTO AppUserGetDTO = DTOMapper.INSTANCE.convertEntityToAppUserGetDTO(AppUser);

        // check content
        assertEquals(AppUser.getId(), AppUserGetDTO.getId());
        assertEquals(AppUser.getPassword(), AppUserGetDTO.getPassword());
        assertEquals(AppUser.getAppUsername(), AppUserGetDTO.getAppUsername());
        assertEquals(AppUser.getStatus(), AppUserGetDTO.getStatus());
    }
}

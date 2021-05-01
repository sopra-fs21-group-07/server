package sopra.userauthentication.mapper;

import org.junit.jupiter.api.Test;
import sopra.userauthentication.dto.GetUser;
import sopra.userauthentication.model.User;
import sopra.userauthentication.model.UserStatus;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void convertUsertoGetUser() {
        //create User
        User user = new User();
        user.setFirstName("Max");
        user.setLastName("Muster");
        user.setUsername("max123");
        user.setAge(20);
        user.setRegion("Zürich");
        user.setUserStatus(UserStatus.OFFLINE);
        user.setEmail("max.muster@abc.ch");
        user.setPassword("password");
        user.setUserId(1L);
        user.setEnabled(true);
        user.setCreated(Instant.now());

        GetUser getUser = UserMapper.INSTANCE.convertUsertoGetUser(user);

        assertEquals(user.getUsername(), getUser.getUsername());
        assertEquals(user.getFirstName(), getUser.getFirstName());
        assertEquals(user.getLastName(), getUser.getLastName());
        assertEquals(user.getRegion(), getUser.getRegion());
        assertEquals(user.getEmail(), getUser.getEmail());
        assertEquals(user.getAge(), getUser.getAge());
        assertEquals(user.getUserStatus(), getUser.getUserStatus());


    }
}
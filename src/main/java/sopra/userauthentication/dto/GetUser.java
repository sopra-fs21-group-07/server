package sopra.userauthentication.dto;

import lombok.Getter;
import lombok.Setter;
import sopra.userauthentication.model.UserStatus;

@Setter
@Getter
public class GetUser {
    private String firstName;
    private String lastName;
    private String username;
    private int age;
    private String region;
    private UserStatus userStatus;
    private String email;

}

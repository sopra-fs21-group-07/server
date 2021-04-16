package sopra.userauthentication.dto;

import lombok.Getter;
import lombok.Setter;
import sopra.userauthentication.model.UserStatus;

@Setter
@Getter
public class GetUser {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private int age;
    private String region;
    private Boolean enabled;
}

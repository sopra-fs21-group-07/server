package sopra.appuser;

import lombok.Getter;
import lombok.Setter;
import sopra.appuser.AppUserStatus;

@Setter
@Getter
public class AppUserGetDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private int age;
    private String region;
    private AppUserRole appUserRole;
    private AppUserStatus appUserStatus;
    private Boolean locked;
    private Boolean enabled;


}

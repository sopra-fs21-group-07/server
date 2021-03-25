package sopra.appuser;

import lombok.Getter;
import lombok.Setter;
import sopra.appuser.AppUserStatus;

@Setter
@Getter
public class AppUserGetDTO {
    private Long id;
    private String email;

}

package sopra.login;

import lombok.*;
import sopra.appuser.AppUser;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginRequest {
    private String email;
    private String password;
}

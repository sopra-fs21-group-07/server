package sopra.login;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopra.appuser.AppUserService;

@RestController
@RequestMapping(path = "/login")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final AppUserService appUserService;

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody LoginRequest request) {
        return loginService.login(request);

    }

}
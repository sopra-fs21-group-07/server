package sopra.login;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/login")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody LoginRequest request) {
        return loginService.login(request);

    }

}
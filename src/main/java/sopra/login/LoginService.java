package sopra.login;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sopra.appuser.AppUser;
import sopra.appuser.AppUserRepository;
import sopra.appuser.AppUserService;
import sopra.appuser.AppUserStatus;
import sopra.registration.token.ConfirmationToken;
import sopra.registration.token.ConfirmationTokenRepository;

import java.util.Optional;


@Service
@AllArgsConstructor
public class LoginService {

    private final AppUserService appUserService;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final AppUserRepository appUserRepository;

    public String login(LoginRequest request){
        Optional<ConfirmationToken> token = confirmationTokenRepository.findByAppUser_Email(request.getEmail());
        Optional<AppUser> user = appUserRepository.findByEmail(request.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (token.isEmpty() || user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This username or email is wrong");
        }
        else if (!encoder.matches(request.getPassword(), user.get().getPassword())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This Password is wrong");
        }
        else {
            user.get().setAppUserStatus(AppUserStatus.ONLINE);
            appUserRepository.save(user.get());
            return token.get().getToken();
        }
    }
}

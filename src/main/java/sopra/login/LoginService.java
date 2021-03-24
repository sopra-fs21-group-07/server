package sopra.login;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sopra.appuser.AppUserService;
import sopra.registration.token.ConfirmationToken;
import sopra.registration.token.ConfirmationTokenRepository;

import java.util.Optional;


@Service
@AllArgsConstructor
public class LoginService {

    private final AppUserService appUserService;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public String login(LoginRequest request){
        Optional<ConfirmationToken> token = confirmationTokenRepository.findByAppUser_Email(request.getEmail());
        return token.get().getToken();
    }
}

package sopra.appuser;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import sopra.appuser.AppUserRepository;
import sopra.registration.token.ConfirmationToken;
import sopra.registration.token.ConfirmationTokenRepository;
import sopra.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final AppUserRepository appUserRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpAppUser(AppUser appUser) {
        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();
        boolean usernameExists = appUserRepository.findByUsername(appUser.getUsername()).isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This email is already taken");
        }
        else if (usernameExists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This username is already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
    public List<AppUser> getAppUsers() {
        return appUserRepository.findAll();
    }

    public void editUsername(String token, String username){
        if (this.confirmationTokenRepository.findByToken(token).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This token does not exist");
        }
        else{
            ConfirmationToken confirmationToken = this.confirmationTokenRepository.findByToken(token).get();
            long id = confirmationToken.getId();
            if (this.appUserRepository.findById(id).isEmpty()){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "This id is not taken in the database");
            }
            AppUser user = this.appUserRepository.findById(id).get();
            user.setUsername(username);
            appUserRepository.flush();
        }
    }

    public void editFirstName(String token, String firstName){
        if (this.confirmationTokenRepository.findByToken(token).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This token does not exist");
        }
        else{
            ConfirmationToken confirmationToken = this.confirmationTokenRepository.findByToken(token).get();
            long id = confirmationToken.getId();
            if (this.appUserRepository.findById(id).isEmpty()){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "This id is not taken in the database");
            }
            AppUser user = this.appUserRepository.findById(id).get();
            user.setFirstName(firstName);
            appUserRepository.flush();
        }
    }

    public void editLastName(String token, String lastName){
        if (this.confirmationTokenRepository.findByToken(token).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This token does not exist");
        }
        else{
            ConfirmationToken confirmationToken = this.confirmationTokenRepository.findByToken(token).get();
            long id = confirmationToken.getId();
            if (this.appUserRepository.findById(id).isEmpty()){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "This id is not taken in the database");
            }
            AppUser user = this.appUserRepository.findById(id).get();
            user.setLastName(lastName);
            appUserRepository.flush();
        }
    }

    public void editAge(String token, int age){
        if (this.confirmationTokenRepository.findByToken(token).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This token does not exist");
        }
        else{
            ConfirmationToken confirmationToken = this.confirmationTokenRepository.findByToken(token).get();
            long id = confirmationToken.getId();
            if (this.appUserRepository.findById(id).isEmpty()){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "This id is not taken in the database");
            }
            AppUser user = this.appUserRepository.findById(id).get();
            user.setAge(age);
            appUserRepository.flush();
        }
    }

    public void editRegion(String token, String region){
        if (this.confirmationTokenRepository.findByToken(token).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This token does not exist");
        }
        else{
            ConfirmationToken confirmationToken = this.confirmationTokenRepository.findByToken(token).get();
            long id = confirmationToken.getId();
            if (this.appUserRepository.findById(id).isEmpty()){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "This id is not taken in the database");
            }
            AppUser user = this.appUserRepository.findById(id).get();
            user.setRegion(region);
            appUserRepository.flush();
        }
    }

    public void editPassword(String token, String password){
        if (this.confirmationTokenRepository.findByToken(token).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This token does not exist");
        }
        else{
            ConfirmationToken confirmationToken = this.confirmationTokenRepository.findByToken(token).get();
            long id = confirmationToken.getId();
            if (this.appUserRepository.findById(id).isEmpty()){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "This id is not taken in the database");
            }
            AppUser user = this.appUserRepository.findById(id).get();
            String encodedPassword = bCryptPasswordEncoder
                    .encode(password);
            user.setPassword(encodedPassword);
            appUserRepository.flush();
        }
    }
}

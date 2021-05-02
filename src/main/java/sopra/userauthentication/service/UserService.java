package sopra.userauthentication.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sopra.userauthentication.model.User;
import sopra.userauthentication.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String error = "This username does not exist";

    public User getUserByUsername(String username){
        if (!this.userRepository.findByUsername(username).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, error);
        }
        else{
            return this.userRepository.findByUsername(username).get();
        }
    }

    public void editUsername(String username, String newUsername){
        if (this.userRepository.findByUsername(username).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, error);
        }
        else{
            User user = this.userRepository.findByUsername(username).get();
            user.setUsername(newUsername);
            userRepository.flush();
        }
    }

    public void editFirstName(String username, String firstName){
        if (this.userRepository.findByUsername(username).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, error);
        }
        else{
            User user = this.userRepository.findByUsername(username).get();
            user.setFirstName(firstName);
            userRepository.flush();
        }
    }

    public void editLastName(String username, String lastName){
        if (this.userRepository.findByUsername(username).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, error);
        }
        else{
            User user = this.userRepository.findByUsername(username).get();
            user.setLastName(lastName);
            userRepository.flush();
        }
    }

    public void editAge(String username, int age){
        if (this.userRepository.findByUsername(username).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, error);
        }
        else{
            User user = this.userRepository.findByUsername(username).get();
            user.setAge(age);
            userRepository.flush();
        }
    }

    public void editRegion(String username, String region){
        if (this.userRepository.findByUsername(username).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, error);
        }
        else{
            User user = this.userRepository.findByUsername(username).get();
            user.setRegion(region);
            userRepository.flush();
        }
    }

    public void editPassword(String username, String password){
        if (this.userRepository.findByUsername(username).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, error);
        }
        else{
            User user = this.userRepository.findByUsername(username).get();
            user.setPassword(passwordEncoder.encode(password));
            userRepository.flush();
        }
    }


}

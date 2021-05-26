package sopra.userauthentication.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sopra.userauthentication.model.User;
import sopra.userauthentication.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String error = "This username does not exist";

    public User getUserByUsername(String username){
        Optional<User> foundUser = this.userRepository.findByUsername(username);
        if (this.userRepository.findByUsername(username).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, error);
        }
        else{
            if (foundUser.isPresent())
                return foundUser.get();
            else
                return new User();
        }
    }

    public void editUsername(String username, String newUsername){
        Optional<User> foundUser = this.userRepository.findByUsername(username);
        if (this.userRepository.findByUsername(username).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, error);
        }
        else{
            if (this.userRepository.findByUsername(username).isPresent()) {
                User user = this.userRepository.findByUsername(username).get();
                user.setUsername(newUsername);
                userRepository.flush();
            }
        }
    }

    public void editFirstName(String username, String firstName){
        Optional<User> foundUser = this.userRepository.findByUsername(username);
        if (this.userRepository.findByUsername(username).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, error);
        }
        else{
            if (foundUser.isPresent()) {
                User user = foundUser.get();
                user.setFirstName(firstName);
                userRepository.flush();
            }
        }
    }

    public void editLastName(String username, String lastName){
        Optional<User> foundUser = this.userRepository.findByUsername(username);
        if (this.userRepository.findByUsername(username).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, error);
        }
        else{
            if (foundUser.isPresent()) {
                User user = foundUser.get();
                user.setLastName(lastName);
                userRepository.flush();
            }
        }
    }

    public void editAge(String username, int age){
        Optional<User> foundUser = this.userRepository.findByUsername(username);
        if (this.userRepository.findByUsername(username).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, error);
        }
        else{
            if (foundUser.isPresent()) {
                User user = foundUser.get();
                user.setAge(age);
                userRepository.flush();
            }
        }
    }

    public void editRegion(String username, String region){
        Optional<User> foundUser = this.userRepository.findByUsername(username);
        if (this.userRepository.findByUsername(username).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, error);
        }
        else{
            if (foundUser.isPresent()) {
                User user = foundUser.get();
                user.setRegion(region);
                userRepository.flush();
            }
        }
    }

    public void editPassword(String username, String password){
        Optional<User> foundUser = this.userRepository.findByUsername(username);
        if (this.userRepository.findByUsername(username).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, error);
        }
        else{
            if (foundUser.isPresent()) {
                User user = foundUser.get();
                user.setPassword(passwordEncoder.encode(password));
                userRepository.flush();
            }
        }
    }


}

package ch.uzh.ifi.hase.soprafs21.service;

import ch.uzh.ifi.hase.soprafs21.constant.UserStatus;
import ch.uzh.ifi.hase.soprafs21.entity.User;
import ch.uzh.ifi.hase.soprafs21.repository.UserRepository;
import ch.uzh.ifi.hase.soprafs21.rest.mapper.DTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

/**
 * User Service
 * This class is the "worker" and responsible for all functionality related to the user
 * (e.g., it creates, modifies, deletes, finds). The result will be passed back to the caller.
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(@Qualifier("userRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User createUser(User newUser) {
        newUser.setToken(UUID.randomUUID().toString());
        newUser.setStatus(UserStatus.ONLINE);

        User userByUsername = userRepository.findByUsername(newUser.getUsername());
        User userByEmail = userRepository.findByEmail(newUser.getEmail());
        if (userByUsername != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This username already exists");
        }
        else if (userByEmail != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is already used");
        }

        // saves the given entity but data is only persisted in the database once flush() is called
        newUser = userRepository.save(newUser);
        userRepository.flush();

        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public User checkUser(User checkUser){
        User userByUsername = userRepository.findByUsername(checkUser.getUsername());
        User userByEmail = userRepository.findByEmail((checkUser.getUsername()));

        if (userByUsername == null && userByEmail==null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This username or email is wrong");
        }
        else if (userByUsername != null && userByUsername.getPassword().equals(checkUser.getPassword())){
            userByUsername.setStatus(UserStatus.ONLINE);
            return userByUsername;
        }
        else if (userByEmail != null && userByEmail.getPassword().equals(checkUser.getPassword())){
            userByEmail.setStatus(UserStatus.ONLINE);
            return userByEmail;
        }
        else{
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This password is wrong");
        }

    }

}

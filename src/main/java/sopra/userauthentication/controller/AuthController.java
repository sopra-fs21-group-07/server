package sopra.userauthentication.controller;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import sopra.userauthentication.dto.*;
import sopra.userauthentication.mapper.UserMapper;
import sopra.userauthentication.model.User;
import sopra.userauthentication.security.JwtProvider;
import sopra.userauthentication.service.AuthService;
import sopra.userauthentication.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sopra.userauthentication.service.UserDetailsServiceImpl;
import sopra.userauthentication.service.UserService;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        try {
            authService.signup(registerRequest);
            return new ResponseEntity<>("User Registration Successful",
                    CREATED);
        }
        catch(ConstraintViolationException e){
            StringBuilder message = new StringBuilder();
            var constraintViolations = e.getConstraintViolations();
            for (var violation : constraintViolations){
                message.append(violation.getMessage().concat(";"));
            }
            return new ResponseEntity<>(String.format("User Registration not successful, %s",message.toString()), HttpStatus.PRECONDITION_FAILED);
        }
        catch(DataIntegrityViolationException f){

            return new ResponseEntity<>("User Registration not successful, email or username is already in use", HttpStatus.PRECONDITION_FAILED);

        }
    }

    @GetMapping("accountVerification/{token}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", OK);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }

    @GetMapping(path = "/profilePage/{username}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GetUser getUser(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return UserMapper.INSTANCE.convertUsertoGetUser(user);
    }
}

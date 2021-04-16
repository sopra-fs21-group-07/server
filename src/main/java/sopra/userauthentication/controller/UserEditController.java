package sopra.userauthentication.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopra.userauthentication.service.UserService;

@RestController
@RequestMapping("/edit")
@AllArgsConstructor
public class UserEditController {

    private UserService userService;

    @PutMapping(path = "/username/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editUsername(@PathVariable String username, @RequestBody String newUsername){
        userService.editUsername(username, newUsername);
    }

    @PutMapping(path = "/firstName/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editFirstName(@PathVariable String username, @RequestBody String firstName){
        userService.editFirstName(username, firstName);
    }

    @PutMapping(path = "/lastName/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editLastName(@PathVariable String username, @RequestBody String lastName){
        userService.editLastName(username, lastName);
    }

    @PutMapping(path = "/age/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editAge(@PathVariable String username, @RequestBody int age){
        userService.editAge(username, age);
    }

    @PutMapping(path = "/region/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editRegion(@PathVariable String username, @RequestBody String region){
        userService.editRegion(username, region);
    }

    @PutMapping(path = "/password/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editPassword(@PathVariable String username, @RequestBody String password){
        userService.editPassword(username, password);
    }

}

package sopra.userauthentication.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopra.userauthentication.dto.editRequests.*;
import sopra.userauthentication.service.UserService;

@RestController
@RequestMapping("/edit")
@AllArgsConstructor
public class UserEditController {

    private UserService userService;

    @PutMapping(path = "/username/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editUsername(@PathVariable String username, @RequestBody PutUsername newUsername){
        userService.editUsername(username, newUsername.getUsername());
    }

    @PutMapping(path = "/firstName/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editFirstName(@PathVariable String username, @RequestBody PutFirstName putFirstName){
        userService.editFirstName(username, putFirstName.getFirstName());
    }

    @PutMapping(path = "/lastName/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editLastName(@PathVariable String username, @RequestBody PutLastName lastName){
        userService.editLastName(username, lastName.getLastName());
    }

    @PutMapping(path = "/age/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editAge(@PathVariable String username, @RequestBody PutAge age){
        userService.editAge(username, age.getAge());
    }

    @PutMapping(path = "/region/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editRegion(@PathVariable String username, @RequestBody PutRegion region){
        userService.editRegion(username, region.getRegion());
    }

    @PutMapping(path = "/password/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editPassword(@PathVariable String username, @RequestBody PutPassword password){
        userService.editPassword(username, password.getPassword());
    }

}

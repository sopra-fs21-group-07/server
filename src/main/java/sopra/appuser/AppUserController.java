package sopra.appuser;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * AppUser Controller
 * This class is responsible for handling all REST request that are related to the AppUser.
 * The controller will receive the request and delegate the execution to the AppUserService and finally return the result.
 */
@RestController
public class AppUserController {

    private final AppUserService AppUserService;

    AppUserController(AppUserService AppUserService) {
        this.AppUserService = AppUserService;
    }

    //can be deleted!
    @GetMapping(path = "/appUsers")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<AppUserGetDTO> getAllAppUsers() {
        // fetch all AppUsers in the internal representation
        List<AppUser> AppUsers = AppUserService.getAppUsers();
        List<AppUserGetDTO> AppUserGetDTOs = new ArrayList<>();

        // convert each AppUser to the API representation
        for (AppUser AppUser : AppUsers) {
            AppUserGetDTOs.add(AppUserMapper.INSTANCE.convertAppUsertoAppUserGetDTO(AppUser));
        }
        return AppUserGetDTOs;
    }

    @GetMapping(path = "/appUsers/{token}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public AppUserGetDTO getUser(@PathVariable String token) {
        AppUser appUser = AppUserService.getAppUser(token);
        return AppUserMapper.INSTANCE.convertAppUsertoAppUserGetDTO(appUser);
    }

    @PutMapping(path = "/appUsers/{token}/editUsername")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editUsername(@PathVariable String token, @RequestBody String username){
        AppUserService.editUsername(token, username);
    }

    @PutMapping(path = "/appUsers/{token}/editFirstName")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editFirstName(@PathVariable String token, @RequestBody String firstName){
        AppUserService.editFirstName(token, firstName);
    }

    @PutMapping(path = "/appUsers/{token}/editLastName")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editLastName(@PathVariable String token, @RequestBody String lastName){
        AppUserService.editLastName(token, lastName);
    }

    @PutMapping(path = "/appUsers/{token}/editAge")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editAge(@PathVariable String token, @RequestBody int age){
        AppUserService.editAge(token, age);
    }

    @PutMapping(path = "/appUsers/{token}/editRegion")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editRegion(@PathVariable String token, @RequestBody String region){
        AppUserService.editRegion(token, region);
    }

    @PutMapping(path = "/appUsers/{token}/editPassword")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editPassword(@PathVariable String token, @RequestBody String password){
        AppUserService.editPassword(token, password);
    }



    /**@PostMapping("/AppUsers")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public AppUserGetDTO createAppUser(@RequestBody AppUserPostDTO AppUserPostDTO) {
        // convert API AppUser to internal representation
        AppUser AppUserInput = DTOMapper.INSTANCE.convertAppUserPostDTOtoEntity(AppUserPostDTO);

        // create AppUser
        AppUser createdAppUser = AppUserService.createAppUser(AppUserInput);

        // convert internal representation of AppUser back to API
        return DTOMapper.INSTANCE.convertEntityToAppUserGetDTO(createdAppUser);
    }*/

    /**@PutMapping("/AppUsers")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public AppUserGetDTO login(@RequestBody AppUserPutDTO AppUserPutDTO){
        // convert API AppUser to internal representation
        AppUser AppUserInput = DTOMapper.INSTANCE.convertAppUserPutDTOtoEntity(AppUserPutDTO);

        AppUser checkAppUser = AppUserService.checkAppUser(AppUserInput);

        return DTOMapper.INSTANCE.convertEntityToAppUserGetDTO(checkAppUser);
    }*/


}

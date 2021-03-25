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

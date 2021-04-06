package sopra.appuser;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/**
 * DTOMapper
 * This class is responsible for generating classes that will automatically transform/map the internal representation
 * of an entity (e.g., the AppUser) to the external/API representation (e.g., AppUserGetDTO for getting, AppUserPostDTO for creating)
 * and vice versa.
 * Additional mappers can be defined for new entities.
 * Always created one mapper for getting information (GET) and one mapper for creating information (POST).
 */
@Mapper
public interface AppUserMapper {

    AppUserMapper INSTANCE = Mappers.getMapper(AppUserMapper.class);
    @Mapping(source = "id", target = "id")
    @Mapping(source = "email", target = "email")
    AppUserGetDTO convertAppUsertoAppUserGetDTO(AppUser appUser);

/**
 @Mapping(source = "password", target = "password")
 @Mapping(source = "username", target = "username")
 @Mapping(source = "email", target = "email")
 AppUser convertAppUserPostDTOtoEntity(AppUserPostDTO AppUserPostDTO);

 @Mapping(source = "id", target = "id")
 @Mapping(source = "username", target = "username")
 @Mapping(source = "email", target = "email")
 @Mapping(source = "status", target = "status")
 @Mapping(source = "token", target = "token")
 AppUserGetDTO convertEntityToAppUserGetDTO(AppUser AppUser);

 @Mapping(source = "password", target = "password")
 @Mapping(source = "username", target = "username")
 AppUser convertAppUserPutDTOtoEntity(AppUserPutDTO AppUserPutDTO);
 */
}

package sopra.pastTour.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import sopra.pastTour.entity.PastTour;
import sopra.pastTour.rest.dto.PastTourGetDTO;

/**
 * DTOMapper
 * This class is responsible for generating classes that will automatically transform/map the internal representation
 * of an entity (e.g., the User) to the external/API representation (e.g., UserGetDTO for getting, UserPostDTO for creating)
 * and vice versa.
 * Additional mappers can be defined for new entities.
 * Always created one mapper for getting information (GET) and one mapper for creating information (POST).
 */
@Mapper
public interface DTOMapperPastTour {
    DTOMapperPastTour INSTANCE = Mappers.getMapper(DTOMapperPastTour.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "summit", target = "summit")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "tourPictureKey", target = "tourPictureKey")
    @Mapping(source = "name", target = "name")
    PastTourGetDTO convertEntityToTourGetDTO(PastTour pastTour);
}
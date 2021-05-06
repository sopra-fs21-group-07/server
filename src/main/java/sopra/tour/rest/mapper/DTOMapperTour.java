package sopra.tour.rest.mapper;

import sopra.tour.entity.Tour;
import sopra.tour.rest.dto.TourGetDTO;
import sopra.tour.rest.dto.TourPostDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import sopra.tour.rest.dto.TourPutDTO;

/**
 * DTOMapper
 * This class is responsible for generating classes that will automatically transform/map the internal representation
 * of an entity (e.g., the User) to the external/API representation (e.g., UserGetDTO for getting, UserPostDTO for creating)
 * and vice versa.
 * Additional mappers can be defined for new entities.
 * Always created one mapper for getting information (GET) and one mapper for creating information (POST).
 */
@Mapper
public interface DTOMapperTour {
    DTOMapperTour INSTANCE = Mappers.getMapper(DTOMapperTour.class);

    @Mapping(source = "type", target = "type")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "summit", target = "summit")
    @Mapping(source = "altitude", target = "altitude")
    @Mapping(source = "emailMember", target = "emailMember")
    @Mapping(source = "tourPictureKey", target = "tourPictureKey")
    Tour convertTourPostDTOtoEntity(TourPostDTO tourPostDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "summit", target = "summit")
    @Mapping(source = "emailMember", target = "emailMember")
    @Mapping(source = "emptySlots", target = "emptySlots")
    //@Mapping(source = "tourPictureKey", target = "tourPictureKey")
    TourGetDTO convertEntityToTourGetDTO(Tour tour);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "emailMember", target="emailMember")
    //@Mapping(source = "tourPictureKey", target = "tourPictureKey")
    Tour convertTourPutDTOtoEntity(TourPutDTO tourPutDTO);
}

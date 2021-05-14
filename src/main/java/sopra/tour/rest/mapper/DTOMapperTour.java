package sopra.tour.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import sopra.tour.entity.Tour;
import sopra.tour.entity.TourMember;
import sopra.tour.rest.dto.TourGetDTO;
import sopra.tour.rest.dto.TourMembersGetDTO;
import sopra.tour.rest.dto.TourPostDTO;
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
    @Mapping(source = "date", target = "date")
    Tour convertTourPostDTOtoEntity(TourPostDTO tourPostDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "summit", target = "summit")
    @Mapping(source = "emailMember", target = "emailMember")
    @Mapping(source = "emptySlots", target = "emptySlots")
    @Mapping(source = "tourPictureKey", target = "tourPictureKey")
    @Mapping(source = "date", target = "date")
    TourGetDTO convertEntityToTourGetDTO(Tour tour);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "tourName", target = "tourName")
    TourMembersGetDTO convertEntityToTourMembersGetDTO(TourMember member);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "emailMember", target="emailMember")
    //@Mapping(source = "tourPictureKey", target = "tourPictureKey")
    //@Mapping(source = "date", target="date")
    Tour convertTourPutDTOtoEntity(TourPutDTO tourPutDTO);
}

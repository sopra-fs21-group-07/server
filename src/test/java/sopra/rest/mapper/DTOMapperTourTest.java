package sopra.rest.mapper;

import org.junit.jupiter.api.Test;
import sopra.tour.TourType;
import sopra.tour.entity.Tour;
import sopra.tour.rest.dto.TourGetDTO;
import sopra.tour.rest.dto.TourPostDTO;
import sopra.tour.rest.dto.TourPutDTO;
import sopra.tour.rest.mapper.DTOMapperTour;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * DTOMapperTour Testclass
 * Tests if the mapping between the internal and the external/API representation works.
 */
public class DTOMapperTourTest {
    @Test
    public void testCreateTour_fromTourPostDTO_toTour_success() {
        // create TourPostDTO
        TourPostDTO TourPostDTO = new TourPostDTO();
        TourPostDTO.setName("name");
        TourPostDTO.setAltitude(3073);
        TourPostDTO.setEmailMember("beat.furrer@uzh.ch");
        TourPostDTO.setEmptySlots(5);
        TourPostDTO.setSummit("Bristen");
        TourPostDTO.setType(TourType.ALPIN);

        // MAP -> Create Tour
        Tour Tour = DTOMapperTour.INSTANCE.convertTourPostDTOtoEntity(TourPostDTO);

        // check content
        assertEquals(TourPostDTO.getName(), Tour.getName());
        assertEquals(TourPostDTO.getAltitude(), Tour.getAltitude());
        assertEquals(TourPostDTO.getEmailMember(), Tour.getEmailMember());
        assertEquals(TourPostDTO.getEmptySlots(), Tour.getEmptySlots());
        assertEquals(TourPostDTO.getType(), Tour.getType());
        assertEquals(TourPostDTO.getSummit(), Tour.getSummit());
    }

    @Test
    public void testGetTour_fromTour_toTourGetDTO_success() {
        // create Tour
        Tour Tour = new Tour();
        Tour.setToken("1");
        Tour.setName("name");
        Tour.setEmailMember("beat.furrer@uzh.ch");
        Tour.setEmptySlots(5);
        Tour.setSummit("Bristen");
        Tour.setType(TourType.ALPIN);

        // MAP -> Create TourGetDTO
        TourGetDTO TourGetDTO = DTOMapperTour.INSTANCE.convertEntityToTourGetDTO(Tour);

        // check content
        assertEquals(Tour.getId(), TourGetDTO.getId());
        assertEquals(Tour.getName(), TourGetDTO.getName());
        assertEquals(Tour.getEmailMember(), TourGetDTO.getEmailMember());
        assertEquals(Tour.getEmptySlots(), TourGetDTO.getEmptySlots());
        assertEquals(Tour.getType(), TourGetDTO.getType());
        assertEquals(Tour.getSummit(), TourGetDTO.getSummit());
    }

    @Test
    public void testPutTour_fromTour_toTourPutDTO_success() {
        // create Tour
        TourPutDTO TourPutDTO = new TourPutDTO();
        TourPutDTO.setId((long)150);
        TourPutDTO.setEmailMember("beat.furrer@uzh.ch");

        // MAP -> Create TourGetDTO
        Tour Tour = DTOMapperTour.INSTANCE.convertTourPutDTOtoEntity(TourPutDTO);

        // check content
        assertEquals(Tour.getId(), TourPutDTO.getId());
        assertEquals(Tour.getEmailMember(), TourPutDTO.getEmailMember());
    }
}

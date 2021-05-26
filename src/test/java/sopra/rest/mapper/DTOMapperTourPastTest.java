package sopra.rest.mapper;

import org.junit.jupiter.api.Test;
import sopra.pastTour.entity.PastTour;
import sopra.pastTour.rest.dto.PastTourGetDTO;
import sopra.pastTour.rest.mapper.DTOMapperPastTour;
import sopra.tour.TourType;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DTOMapperTourPastTest {

    @Test
    public void testGetTour_fromTour_toTourGetDTO_success() {
        // create Tour
        PastTour pastTour = new PastTour();
        pastTour.setId((long) 1);
        pastTour.setDate(new Date());
        pastTour.setType(TourType.ALPIN);
        pastTour.setSummit("Bristen");

        // PastTour -> Create TourGetDTO
        PastTourGetDTO pastTourGetDTO = DTOMapperPastTour.INSTANCE.convertEntityToTourGetDTO(pastTour);

        // check content
        assertEquals(pastTour.getId(), pastTourGetDTO.getId());
        assertEquals(pastTour.getDate(), pastTourGetDTO.getDate());
        assertEquals(pastTour.getSummit(), pastTourGetDTO.getSummit());
        assertEquals(pastTour.getType(), pastTourGetDTO.getType());
    }
}

package sopra.rest.mapper;

import org.junit.jupiter.api.Test;
import sopra.tour.TourType;
import sopra.tour.entity.TourMember;
import sopra.tour.rest.dto.TourMembersGetDTO;
import sopra.tour.rest.mapper.DTOMapperTour;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * DTOMapperTest
 * Tests if the mapping between the internal and the external/API representation works.
 */
public class DTOMapperTest {
    @Test
    public void testGetTourMembers_fromTour_toTourMembersGetDTO_success() {
        // create Tour
        TourMember tourMember = new TourMember();
        tourMember.setTourName("testTour");
        tourMember.setId(1L);
        tourMember.setUseremail("testuser@uzh.ch");
        tourMember.setUsername("testUser01");

        // MAP -> Create TourGetDTO
        TourMembersGetDTO tourMembersGetDTO = DTOMapperTour.INSTANCE.convertEntityToTourMembersGetDTO(tourMember);

        // check content
        assertEquals(tourMember.getId(), tourMembersGetDTO.getId());
        assertEquals(tourMember.getTourName(), tourMembersGetDTO.getTourName());
        assertEquals(tourMember.getUseremail(), tourMembersGetDTO.getUseremail());
        assertEquals(tourMember.getUsername(), tourMembersGetDTO.getUsername());
    }

    @Test
    public void testPostSummit_fromSummitPostDTO_toSummit_success() {
        // create Summit
        assertNotNull(TourType.valueOf("ALPIN"));
        assertNotNull(TourType.valueOf("SKI_SNOWBOARD_TOUR"));
        assertNotNull(TourType.valueOf("SNOWSHOE"));
        assertNotNull(TourType.valueOf("FREERIDE"));
        assertNotNull(TourType.valueOf("CLIMBING"));
        assertNotNull(TourType.valueOf("HIKING"));
        assertNotNull(TourType.valueOf("CANYONING"));
        assertNotNull(TourType.valueOf("BIKE"));
    }
}

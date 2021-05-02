package sopra.rest.mapper;

import org.junit.jupiter.api.Test;
import sopra.mapAPI.entity.Summit;
import sopra.mapAPI.rest.dto.MapFoundGetDTO;
import sopra.mapAPI.rest.dto.MapSearchPostDTO;
import sopra.mapAPI.rest.mapper.DTOMapperMapAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * DTOMapperTour Testclass
 * Tests if the mapping between the internal and the external/API representation works.
 */
public class DTOMapperMap {
    @Test
    public void testCreateMapFoundGetDTO_fromSummit_Mapper() {
        // create MapFoundGetDTO
        MapFoundGetDTO testMap = new MapFoundGetDTO();
        testMap.setName("name");
        testMap.setAltitude(3073);
        testMap.setX(200000);
        testMap.setY(600000);

        Summit testSummit = new Summit();
        testSummit.setName("name");
        testSummit.setAltitude(3073);
        testSummit.setX(200000);
        testSummit.setY(600000);

        // MAP -> Create map
        MapFoundGetDTO mapFoundGetDTO = DTOMapperMapAPI.INSTANCE.convertEntityToTourGetDTO(testSummit);

        // check content
        assertEquals(mapFoundGetDTO.getName(), testMap.getName());
        assertEquals(mapFoundGetDTO.getAltitude(), testMap.getAltitude());
        assertEquals(mapFoundGetDTO.getX(), testMap.getX());
        assertEquals(mapFoundGetDTO.getY(), testMap.getY());
    }

    @Test
    public void testGetTour_fromTour_toTourGetDTO_success() {
        // create Tour
        MapSearchPostDTO mapSearchPostDTO = new MapSearchPostDTO();
        mapSearchPostDTO.setUserInput("Bristen");

        // MAP -> Create TourGetDTO
        Summit summit = DTOMapperMapAPI.INSTANCE.convertMapSearchToSearchText(mapSearchPostDTO);

        // check content
        assertEquals(summit.getName(), mapSearchPostDTO.getUserInput());
    }
}

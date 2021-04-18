package sopra.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import sopra.tour.entity.Tour;
import sopra.tour.repository.TourRepository;
import sopra.tour.service.TourService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the AppUserResource REST resource.
 *
 * @see TourService
 */
@WebAppConfiguration
@SpringBootTest
public class TourServiceTest {

    @Qualifier("tourRepository")
    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourService tourService;

    @BeforeEach
    public void setup() {
        tourRepository.deleteAll();
    }

    @Test
    public void CheckTourCreation() {
        // given
        assertNull(tourRepository.findByName("testAppUsername"));

        Tour testTour = new Tour();
        testTour.setName("testName");
        testTour.setSummit("Bristen");
        testTour.setAltitude(3073);

        // when
        Tour createdTour = tourService.createTour(testTour);

        // then
        assertEquals(testTour.getId(), createdTour.getId());
        assertEquals(testTour.getName(), createdTour.getName());
        assertEquals(testTour.getSummit(), createdTour.getSummit());
        assertEquals(testTour.getAltitude(), createdTour.getAltitude());
        assertNotNull(createdTour.getToken());
    }

    @Test
    public void CheckCoordinateCalculation() {
        // given
        assertNull(tourRepository.findByName("testAppUsername"));

        Tour testTour = new Tour();
        testTour.setName("testName");
        testTour.setSummit("Bristen");
        testTour.setAltitude(3073);

        // when
        Tour createdTour = tourService.createTour(testTour);

        // then
        assertEquals(testTour.getSummit(), createdTour.getSummit());
        assertEquals(testTour.getY_LV03(), 694976);
        assertEquals(testTour.getX_LV03(), 176940);
        System.out.print("Test: " + testTour.getEast_WGS() + " / " + testTour.getNorth_WGS());
        assertEquals(testTour.getEast_WGS(), 8.68135, 0.0001);
        assertEquals(testTour.getNorth_WGS(), 46.73690, 0.0001);
        assertNotNull(createdTour.getToken());
    }
}

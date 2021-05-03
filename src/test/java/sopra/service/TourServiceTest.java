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

import java.util.ArrayList;
import java.util.List;

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
    public void CheckTourCreation() throws Exception {
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
    public void CheckCoordinateCalculation() throws Exception {
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

    @Test
    public void CheckKMLFile() throws Exception {
        Tour testTour = new Tour();
        testTour.setId((long)1);
        testTour.setName("testName");
        testTour.setSummit("Bristen");
        testTour.setAltitude(3073);

        List<Tour> tours = new ArrayList<>();
        tours.add(testTour);

        String testKML = "<kml xmlns=\\\"http://www.opengis.net/kml/2.2\\\" xmlns:gx=\\\"http://www.google.com/kml/ext/2.2\\\" xmlns:xsi=\\\"http://www.w3.org/2001/XMLSchema-instance\\\" xsi:schemaLocation=\\\"http://www.opengis.net/kml/2.2 https://developers.google.com/kml/schema/kml22gx.xsd\\\"><Document><name>Zeichnung</name><Placemark id=\\\"marker_1\\\"><ExtendedData><Data name=\\\"type\\\"><value>marker</value></Data></ExtendedData><name>testName</name><description>Link: &lt;a target=\\\"_blank\\\" href=http://localhost:3000/confirmTour/1&gt; Tour details: testName&lt;/a&gt;&lt;style=\\\"max-height:200px;\\\"/&gt;</description><Style><IconStyle><scale>0.75</scale><Icon><href>https://api3.geo.admin.ch/color/255,0,0/triangle-24@2x.png</href><gx:w>48</gx:w><gx:h>48</gx:h></Icon></IconStyle><LabelStyle><color>ff00ffff</color></LabelStyle></Style><Point><tessellate>1</tessellate><altitudeMode>clampToGround</altitudeMode><coordinates>0.0,0.0,3073</coordinates></Point></Placemark></Document></kml>";

        // when
        String createKMLFile = tourService.createKMLFile(tours);

        assertEquals(testKML, createKMLFile);
    }

    @Test
    public void CheckAddTeamMember() throws Exception {
        Tour testTour = new Tour();
        testTour.setId((long)1);
        testTour.setName("testName");
        testTour.setSummit("Bristen");
        testTour.setAltitude(3073);
        testTour.setEmptySlots(1);

        String returnUserinput = tourService.add(testTour, testTour);

        assertEquals("0", returnUserinput);
    }

    @Test
    public void CheckReturnAllTours() throws Exception {
        List<Tour> tours = tourService.getTours();

        assertEquals(true, tours.isEmpty());
    }

    /*@Test
    public void CheckReturnGetTourById() throws Exception {
        // given
        assertNull(tourRepository.findByName("testAppUsername"));

        Tour testTour = new Tour();
        testTour.setName("testName");
        testTour.setSummit("Bristen");
        testTour.setAltitude(3073);
        tourService.createTour(testTour);

        // when
        Tour returnTour = tourService.getTourById((long) 0);

        // then
        assertEquals(testTour.getId(), returnTour.getId());
        assertEquals(testTour.getName(), returnTour.getName());
        assertEquals(testTour.getSummit(), returnTour.getSummit());
        assertEquals(testTour.getAltitude(), returnTour.getAltitude());
        assertNotNull(returnTour.getToken());
    }*/
}

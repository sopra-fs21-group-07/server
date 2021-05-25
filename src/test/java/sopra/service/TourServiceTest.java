package sopra.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.server.ResponseStatusException;
import sopra.tour.TourType;
import sopra.tour.entity.Summit;
import sopra.tour.entity.Tour;
import sopra.tour.repository.SummitRepository;
import sopra.tour.repository.TourMembersRepository;
import sopra.tour.repository.TourRepository;
import sopra.tour.service.TourService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

/**
 * Test class for the AppUserResource REST resource.
 *
 * @see TourService
 */
@WebAppConfiguration
@SpringBootTest
public class TourServiceTest {

    @Mock
    private TourRepository tourRepository;

    @Qualifier("summitRepository")
    @Autowired
    private SummitRepository summitRepository;

    @Qualifier("tourMembersRepository")
    @Autowired
    private TourMembersRepository tourMembersRepository;

    @Autowired
    private TourService tourService;

    @InjectMocks
    private TourService tourServiceInjected;

    private Tour testTour;

    @BeforeEach
    public void setup() {
        tourRepository.deleteAll();
        summitRepository.deleteAll();

        MockitoAnnotations.openMocks(this);

        testTour = new Tour();
        testTour.setType(TourType.SKI_SNOWBOARD_TOUR);
        testTour.setToken("bla");
        testTour.setEmptySlots(5);
        testTour.setTourPictureKey("bla");
        testTour.setEmailMember("bla");
        testTour.setCreatorUsername("bla");
        testTour.setName("testName1");
        testTour.setSummit("Bristen");
        testTour.setId(1L);
        testTour.setAltitude(3073);
        testTour.setDate(LocalDate.now());
    }

    @Test
    public void CheckTourCreation() throws Exception {
        // given
        assertNull(tourRepository.findByName("testAppUsername"));

        Tour testTour = new Tour();
        testTour.setEmailMember("bla");
        testTour.setCreatorUsername("bla");
        testTour.setName("testName1");
        testTour.setSummit("Bristen");
        testTour.setAltitude(3073);
        testTour.setDate(LocalDate.now());

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
        testTour.setName("testName2");
        testTour.setSummit("Bristen");
        testTour.setEmailMember("bla");
        testTour.setCreatorUsername("bla");
        testTour.setAltitude(3073);
        testTour.setDate(LocalDate.now());

        Summit testSummit = new Summit();
        testSummit.setCoordinate_LV03(new int[]{694976, 176940});
        testSummit.setCoordinate_WGS(new double[]{8.68135, 46.73690});

        // when
        Tour createdTour = tourService.createTour(testTour);

        // then
        assertEquals(testTour.getSummit(), createdTour.getSummit());
        assertEquals(testSummit.getY_LV03(), 694976);
        assertEquals(testSummit.getX_LV03(), 176940);
        System.out.print("Test: " + testSummit.getEast_WGS() + " / " + testSummit.getNorth_WGS());
        assertEquals(testSummit.getEast_WGS(), 8.68135, 0.0001);
        assertEquals(testSummit.getNorth_WGS(), 46.73690, 0.0001);
        assertNotNull(createdTour.getToken());
    }

    @Test
    public void CheckKMLFile() throws Exception {
        Tour testTour = new Tour();
        testTour.setId((long)1);
        testTour.setName("testName");
        testTour.setEmailMember("bla");
        testTour.setCreatorUsername("bla");
        testTour.setSummit("Bristen");
        testTour.setAltitude(3073);

        Summit testSummit = new Summit();
        testSummit.setCoordinate_LV03(new int[]{694976, 176940});
        testSummit.setCoordinate_WGS(new double[]{8.6, 46.7});
        testSummit.setName("Bristen");
        testSummit.setAltitude(3073);

        List<Tour> tours = new ArrayList<>();
        List<Summit> summits = new ArrayList<>();
        tours.add(testTour);
        summits.add(testSummit);

        String testKML = "<kml xmlns=\\\"http://www.opengis.net/kml/2.2\\\" xmlns:gx=\\\"http://www.google.com/kml/ext/2.2\\\" xmlns:xsi=\\\"http://www.w3.org/200" +
                "1/XMLSchema-instance\\\" xsi:schemaLocation=\\\"http://www.opengis.net/kml/2.2 https://developers.google.com/kml/schema/kml22gx.xsd\\\"><Document><name>" +
                "Zeichnung</name><Placemark id=\\\"marker_1\\\"><ExtendedData><Data name=\\\"type\\\"><value>marker</value></Data></ExtendedData><name>testName</name>" +
                "<description>Link: &lt;a target=\\\"_blank\\\" href=http://localhost:3000/confirmTour/1&gt; Tour details: testName&lt;/a&gt;&lt;style=\\\"max-height:" +
                "200px;\\\"/&gt;</description><Style><IconStyle><scale>0.75</scale><Icon><href>https://api3.geo.admin.ch/color/255,0,0/triangle-24@2x.png</href><gx:w>48" +
                "</gx:w><gx:h>48</gx:h></Icon></IconStyle><LabelStyle><color>ff00ffff</color></LabelStyle></Style><Point><tessellate>1</tessellate><altitudeMode>clampToG" +
                "round</altitudeMode><coordinates>8.6,46.7,3073</coordinates></Point></Placemark></Document></kml>";

        // when
        String createKMLFile = tourService.createKMLFile(tours, summits);

        assertEquals(testKML, createKMLFile);
    }
    /*
    @Test
    public void CheckAddTeamMember() throws Exception {
        Tour testTour = new Tour();
        testTour.setId((long)1);
        testTour.setName("testName4");
        testTour.setCreatorUsername("bla");
        testTour.setSummit("Bristen");
        testTour.setAltitude(3073);
        testTour.setEmptySlots(1);
        testTour.setEmailMember("test@uzh.ch");
        testTour.setDate(LocalDate.now());

        String returnUserinput = tourService.add(testTour, testTour);

        assertEquals("0", returnUserinput);
    }**/

    @Test
    public void CheckReturnAllTours() throws Exception {
        List<Tour> tours = tourService.getTours();

        assertEquals(true, tours.isEmpty());
    }

    @Test
    public void getTourById(){
        Mockito.when(tourRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(testTour));

        Tour tour = tourServiceInjected.getTourById(1L);
        Mockito.verify(tourRepository, Mockito.times(1)).findById(Mockito.anyLong());

        assertEquals(testTour.getName(), tour.getName());
        assertEquals(testTour.getSummit(), tour.getSummit());
        assertEquals(testTour.getAltitude(), tour.getAltitude());
        assertEquals(testTour.getId(), tour.getId());
        assertEquals(testTour.getCreatorUsername(), tour.getCreatorUsername());
    }

    @Test
    public void editName() {
        Mockito.when(this.tourRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(testTour));
        tourServiceInjected.editName(1L, "Säntis");

        Mockito.verify(tourRepository, Mockito.times(2)).findById(Mockito.anyLong());
        Mockito.verify(tourRepository, Mockito.times(1)).flush();


        assertEquals("Säntis", testTour.getName());
    }

    @Test
    public void editEmptySlots() {
        Mockito.when(this.tourRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(testTour));
        tourServiceInjected.editEmptySlots(1L, 99);

        Mockito.verify(tourRepository, Mockito.times(2)).findById(Mockito.anyLong());
        Mockito.verify(tourRepository, Mockito.times(1)).flush();


        assertEquals(99, testTour.getEmptySlots());
    }

    @Test
    public void deleteTour() {
        Mockito.when(this.tourRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(testTour));
        tourServiceInjected.deleteTour(1L);

        Mockito.verify(tourRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(tourRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verify(tourRepository, Mockito.times(1)).flush();
    }

    @Test
    void MethodsFail() {
        Mockito.when(this.tourRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> tourServiceInjected.editName(1L, "max"));
        assertThrows(ResponseStatusException.class, () -> tourServiceInjected.editEmptySlots(1L, 66));
        assertThrows(ResponseStatusException.class, () -> tourServiceInjected.deleteTour(1L));
    }
}

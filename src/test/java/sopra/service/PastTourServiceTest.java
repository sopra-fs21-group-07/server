package sopra.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import sopra.pastTour.entity.PastTour;
import sopra.pastTour.repository.PastTourRepository;
import sopra.pastTour.service.PastTourService;
import sopra.tour.TourType;
import sopra.tour.repository.SummitRepository;
import sopra.tour.repository.TourMembersRepository;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@WebAppConfiguration
@SpringBootTest
public class PastTourServiceTest {

    @Mock
    private PastTourRepository pastTourRepository;

    @Qualifier("summitRepository")
    @Autowired
    private SummitRepository summitRepository;

    @Qualifier("tourMembersRepository")
    @Autowired
    private TourMembersRepository tourMembersRepository;

    @Autowired
    private PastTourService pastTourService;

    @InjectMocks
    private PastTourService tourServiceInjected;

    private PastTour pastTestTour;

    @BeforeEach
    public void setup() {
        pastTourRepository.deleteAll();
        summitRepository.deleteAll();

        MockitoAnnotations.openMocks(this);

        pastTestTour = new PastTour();
        pastTestTour.setType(TourType.SKI_SNOWBOARD_TOUR);
        pastTestTour.setSummit("Bristen");
        pastTestTour.setId(1L);
        pastTestTour.setDate(new Date());
    }

    @Test
    public void test_createPastTour() throws Exception {
        // given
        assertNull(pastTourRepository.findBySummit("testSummit"));

        // create Tour
        PastTour pastTour = new PastTour();
        pastTour.setId((long) 1);
        pastTour.setDate(new Date());
        pastTour.setType(TourType.ALPIN);
        pastTour.setSummit("Bristen");

        // when
        PastTour createdTour = pastTourService.createPastTour(pastTour);

        // then
        assertEquals(pastTour.getId(), createdTour.getId());
        assertEquals(pastTour.getType(), createdTour.getType());
        assertEquals(pastTour.getSummit(), createdTour.getSummit());
        assertEquals(pastTour.getDate(), createdTour.getDate());
    }

    @Test
    public void test_getAllPastTours() throws Exception {
        // given
        assertNull(pastTourRepository.findBySummit("testSummit"));

        // create Tour
        PastTour pastTour = new PastTour();
        pastTour.setId((long) 1);
        pastTour.setDate(new Date());
        pastTour.setType(TourType.ALPIN);
        pastTour.setSummit("Bristen");

        // when
        PastTour createdTour = pastTourService.createPastTour(pastTour);
        List<PastTour> pastTours = pastTourService.getPastTours();

        assertEquals(pastTours.get(0).getId(), createdTour.getId());
        assertEquals(pastTours.get(0).getType(), createdTour.getType());
        assertEquals(pastTours.get(0).getSummit(), createdTour.getSummit());
        //assertEquals(pastTours.get(0).getDate(), createdTour.getDate());
    }
}
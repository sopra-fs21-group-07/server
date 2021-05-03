package sopra.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import sopra.tour.controller.TourController;
import sopra.tour.repository.TourRepository;
import sopra.tour.rest.dto.TourGetDTO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@WebAppConfiguration
@SpringBootTest
public class TourControllerTest {

    @Qualifier("tourRepository")
    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void returnAllTours() throws Exception {
        List<TourGetDTO> tourGetDTOS = controller.getAllTours();

        assertThat(controller).isNotNull();
        assertEquals(tourGetDTOS.isEmpty(), true);
    }
}

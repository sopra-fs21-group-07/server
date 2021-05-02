package sopra.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sopra.tour.controller.TourController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TourControllerTest {
    @Autowired
    private TourController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}

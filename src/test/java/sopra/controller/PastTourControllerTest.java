package sopra.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import sopra.pastTour.controller.PastTourController;
import sopra.pastTour.rest.dto.PastTourGetDTO;
import sopra.pastTour.service.PastTourService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class PastTourControllerTest {

    @Autowired
    private PastTourController controller;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PastTourService service;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void returnAllTours() throws Exception {
        List<PastTourGetDTO> pasttourGetDTOS = controller.getAllTours();

        assertThat(controller).isNotNull();
        assertEquals(pasttourGetDTOS.isEmpty(), true);
    }
}

package sopra.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.server.ResponseStatusException;
import sopra.tour.TourType;
import sopra.tour.controller.TourController;
import sopra.tour.repository.TourRepository;
import sopra.tour.rest.dto.TourGetDTO;
import sopra.tour.rest.dto.TourPostDTO;
import sopra.tour.service.TourService;
import sopra.userauthentication.dto.AuthenticationResponse;
import sopra.userauthentication.dto.LoginRequest;
import sopra.userauthentication.dto.RefreshTokenRequest;
import sopra.userauthentication.dto.RegisterRequest;
import sopra.userauthentication.model.User;
import sopra.userauthentication.service.AuthService;
import sopra.userauthentication.service.RefreshTokenService;
import sopra.userauthentication.service.UserService;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@AutoConfigureMockMvc
public class TourControllerTest {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourController controller;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TourService tourService;

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

    @Test
    void verifyGetRequest() throws Exception {
        MockHttpServletRequestBuilder postRequest = get("/tours")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(postRequest).andReturn();

        verify(tourService,times(1)).getTours();

        // test the http status of the response
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        // test the http method ()
        assertEquals(HttpMethod.GET.name(), result.getRequest().getMethod());
    }

    @Test
    void verifyPostRequest() throws Exception {
        TourPostDTO tourPostDTO = new TourPostDTO();
        tourPostDTO.setAltitude(3073);
        tourPostDTO.setEmptySlots(10);
        tourPostDTO.setName("MyTestTour");
        tourPostDTO.setSummit("Bristen");
        tourPostDTO.setType(TourType.ALPIN);

        MockHttpServletRequestBuilder postRequest = post("/tours")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(tourPostDTO));

        MvcResult result = mockMvc.perform(postRequest).andReturn();

        //verify(tourService,times(1)).getTours();

        // test the http status of the response
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());

        // test the http method ()
        assertEquals(HttpMethod.POST.name(), result.getRequest().getMethod());
    }

    private String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        }
        catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("The request body could not be created.%s", e.toString()));
        }
    }
}

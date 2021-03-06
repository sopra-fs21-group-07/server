package sopra.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void verifyGetRequest_TourMember() throws Exception {
        MockHttpServletRequestBuilder getRequest = get("/tourMembers")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(getRequest).andReturn();

        verify(tourService,times(1)).getTourMembers();

        // test the http status of the response
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        // test the http method ()
        assertEquals(HttpMethod.GET.name(), result.getRequest().getMethod());
    }

    @Test
    void enumType_TourType() throws Exception {
        assertEquals("SKI_SNOWBOARD_TOUR", TourType.SKI_SNOWBOARD_TOUR.name());
        assertEquals("SNOWSHOE", TourType.SNOWSHOE.name());
        assertEquals("FREERIDE", TourType.FREERIDE.name());
        assertEquals("CLIMBING", TourType.CLIMBING.name());
        assertEquals("ALPIN", TourType.ALPIN.name());
        assertEquals("HIKING", TourType.HIKING.name());
        assertEquals("CANYONING", TourType.CANYONING.name());
        assertEquals("BIKE", TourType.BIKE.name());
    }

    /*@Test
    void updateTourById_success() throws Exception{
        Tour tour = new Tour();
        tour.setId(1L);
        tour.setEmptySlots(2);
        tour.setDate(LocalDate.now());
        tour.setEmailMember("testUser@uzh.ch");
        tour.setName("MyTestTour");
        tour.setSummit("Bristen");
        tour.setType(TourType.ALPIN);

        TourPostDTO tourPostDTO = new TourPostDTO();
        tourPostDTO.setAltitude(3073);
        tourPostDTO.setEmptySlots(10);
        tourPostDTO.setName("MyTestTour");
        tourPostDTO.setSummit("Bristen");
        tourPostDTO.setType(TourType.ALPIN);

        MockHttpServletRequestBuilder postRequest = post("/tours")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(tourPostDTO));

        Mockito.when(tourService.add(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn("Hello");
        String json = "  \"type\": \"CLIMBING\",\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"myTour\",\n" +
                "        \"summit\": \"Gemsstock\",\n" +
                "        \"emailMember\": null,\n" +
                "        \"emptySlots\": 2,\n" +
                "        \"tourPictureKey\": \"sampleTourPicture\",\n" +
                "        \"date\": 1622160000000,\n" +
                "        \"creatorUsername\": \"elBeato\"";

        mockMvc.perform(post("/tours").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect((ResultMatcher) jsonPath("$.id", Matchers.equalTo(1L)))
                .andExpect((ResultMatcher) jsonPath("$.emailMember", Matchers.equalTo("MyTestTour")));

        mockMvc.perform(put("/tours/1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect((ResultMatcher) jsonPath("$.[0].id", Matchers.equalTo(1L)))
                .andExpect((ResultMatcher) jsonPath("$.[0].emailMember", Matchers.equalTo("MyTestTour")));
    }*/

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

        // test the http status of the response
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());

        // test the http method ()
        assertEquals(HttpMethod.POST.name(), result.getRequest().getMethod());
    }

    @Test
    void verifyPutRequest() throws Exception {
        TourPostDTO tourPostDTO = new TourPostDTO();
        tourPostDTO.setAltitude(3073);
        tourPostDTO.setEmptySlots(10);
        tourPostDTO.setName("MyTestTour");
        tourPostDTO.setSummit("Bristen");
        tourPostDTO.setType(TourType.ALPIN);

        MockHttpServletRequestBuilder postRequest = put("/tours/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(tourPostDTO));

        MvcResult result = mockMvc.perform(postRequest).andReturn();

        // test the http status of the response
        assertEquals(HttpStatus.ACCEPTED.value(), result.getResponse().getStatus());

        // test the http method ()
        assertEquals(HttpMethod.PUT.name(), result.getRequest().getMethod());
    }

    @Test
    void verifyDeleteRequest() throws Exception {
        MockHttpServletRequestBuilder postRequest = delete("/tours/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(1L));

        MvcResult result = mockMvc.perform(postRequest).andReturn();

        // test the http status of the response
        assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());

        // test the http method ()
        assertEquals(HttpMethod.DELETE.name(), result.getRequest().getMethod());
    }

    @Test
    void verifyDeleteTourMemberRequest() throws Exception {
        MockHttpServletRequestBuilder postRequest = delete("/tourMembers/1/testUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(1L));

        MvcResult result = mockMvc.perform(postRequest).andReturn();

        // test the http status of the response
        assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());

        // test the http method ()
        assertEquals(HttpMethod.DELETE.name(), result.getRequest().getMethod());
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

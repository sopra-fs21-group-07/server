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
import sopra.map_api.controller.ApiController;
import sopra.map_api.entity.Summit;
import sopra.map_api.rest.dto.MapSearchPostDTO;
import sopra.map_api.service.MapApiService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class APIControllerTest {

    @Autowired
    private ApiController apiController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MapApiService mapApiService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(apiController).isNotNull();
    }

    @Test
    public void returnAllTours_MapAPIPost() throws Exception {
        List<Summit> summit = apiController.getAllTours();

        assertThat(apiController).isNotNull();
        assertEquals(summit.isEmpty(), false);
    }

    @Test
    void verifyPostRequest_MapSearchPostDTO() throws Exception {
        MapSearchPostDTO mapSearchPostDTO = new MapSearchPostDTO();
        mapSearchPostDTO.setUserInput("Gitschen");

        MockHttpServletRequestBuilder postRequest = post("/nameGeoMapAdmin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mapSearchPostDTO));

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

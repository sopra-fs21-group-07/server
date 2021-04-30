package sopra.userauthentication.controller;

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
import sopra.userauthentication.dto.editRequests.*;
import sopra.userauthentication.service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
class UserEditControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void editUsername() throws Exception {
        PutUsername putUsername = new PutUsername();
        putUsername.setUsername("max456");

        MockHttpServletRequestBuilder putRequest = put("/edit/username/max123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putUsername));

        // then
        MvcResult result = mockMvc.perform(putRequest).andReturn();

        // verify that userService.editUsername(string, string) is called one time!
        verify(userService,times(1)).editUsername(Mockito.anyString(),Mockito.anyString());

        // test the http status of the response
        assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());

        // test the http method ()
        assertEquals(HttpMethod.PUT.name(), result.getRequest().getMethod());

    }

    @Test
    void editFirstName() throws Exception {
        PutFirstName putFirstName = new PutFirstName();
        putFirstName.setFirstName("Max");

        MockHttpServletRequestBuilder putRequest = put("/edit/firstName/max123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putFirstName));


        MvcResult result = mockMvc.perform(putRequest).andReturn();

        verify(userService,times(1)).editFirstName(Mockito.anyString(),Mockito.anyString());

        // test the http status of the response
        assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());

        // test the http method ()
        assertEquals(HttpMethod.PUT.name(), result.getRequest().getMethod());
    }

    @Test
    void editLastName() throws Exception {
        PutLastName putLastName = new PutLastName();
        putLastName.setLastName("Muster");

        MockHttpServletRequestBuilder putRequest = put("/edit/lastName/max123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putLastName));


        MvcResult result = mockMvc.perform(putRequest).andReturn();

        verify(userService,times(1)).editLastName(Mockito.anyString(),Mockito.anyString());

        // test the http status of the response
        assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());

        // test the http method ()
        assertEquals(HttpMethod.PUT.name(), result.getRequest().getMethod());
    }

    @Test
    void editAge() throws Exception {
        PutAge putAge = new PutAge();
        putAge.setAge(30);

        MockHttpServletRequestBuilder putRequest = put("/edit/age/max123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putAge));


        MvcResult result = mockMvc.perform(putRequest).andReturn();

        verify(userService,times(1)).editAge(Mockito.anyString(),Mockito.anyInt());

        // test the http status of the response
        assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());

        // test the http method ()
        assertEquals(HttpMethod.PUT.name(), result.getRequest().getMethod());
    }

    @Test
    void editRegion() throws Exception {
        PutRegion putRegion = new PutRegion();
        putRegion.setRegion("Genf");

        MockHttpServletRequestBuilder putRequest = put("/edit/region/max123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putRegion));


        MvcResult result = mockMvc.perform(putRequest).andReturn();

        verify(userService,times(1)).editRegion(Mockito.anyString(),Mockito.anyString());

        // test the http status of the response
        assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());

        // test the http method ()
        assertEquals(HttpMethod.PUT.name(), result.getRequest().getMethod());
    }

    @Test
    void editPassword() throws Exception {
        PutPassword putPassword = new PutPassword();
        putPassword.setPassword("pw1234");

        MockHttpServletRequestBuilder putRequest = put("/edit/password/max123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putPassword));


        MvcResult result = mockMvc.perform(putRequest).andReturn();

        verify(userService,times(1)).editPassword(Mockito.anyString(),Mockito.anyString());

        // test the http status of the response
        assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());

        // test the http method ()
        assertEquals(HttpMethod.PUT.name(), result.getRequest().getMethod());
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
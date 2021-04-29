package sopra.userauthentication.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
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
        //PLEASE edit this method
        // when/then -> do the request + validate the result
        MockHttpServletRequestBuilder putRequest = put("/edit/username/max1233");

        // then
        MvcResult result = mockMvc.perform(putRequest).andReturn();

        // verify that userService.editUsername(string, string) is called one time!
        verify(userService,times(1)).editUsername(Mockito.anyString(),Mockito.anyString());

        // test the http status of the response
        assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());

        // test the http method ()
        assertEquals(HttpMethod.PUT.name(), result.getRequest().getMethod());

        // test the content type of the response
        assertNull(result.getRequest().getContentType());
    }

    @Test
    void editFirstName() {
    }

    @Test
    void editLastName() {
    }

    @Test
    void editAge() {
    }

    @Test
    void editRegion() {
    }

    @Test
    void editPassword() {
    }
}
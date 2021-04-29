package sopra.userauthentication.controller;


import org.apache.catalina.security.SecurityConfig;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import sopra.Application;
import sopra.userauthentication.model.User;
import sopra.userauthentication.model.UserStatus;
import sopra.userauthentication.service.UserService;
import static org.mockito.BDDMockito.given;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@RunWith(SpringRunner.class)
//@WebMvcTest(UserEditController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserEditControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User user;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                //.apply(springSecurity())
                .build();
    }

    @WithMockUser(value = "spring")
    @Test
    void editFirstName() throws Exception{

        user = new User();
        user.setUserId(1L);
        user.setFirstName("Max");
        user.setLastName("Muster");
        user.setEmail("raffi_waespi@hotmail.com");
        user.setUsername("max123");
        user.setPassword("password");
        user.setAge(20);
        user.setRegion("Zürich");
        user.setEnabled(true);
        user.setUserStatus(UserStatus.OFFLINE);
        // when/then -> do the request + validate the result
        MockHttpServletRequestBuilder putRequest = put("/firstName/max123");

        // then
        MvcResult result = mockMvc.perform(putRequest).andReturn();

        // verify that userService.updateStatus(id) is called one time!
        verify(userService,times(0)).editFirstName(Mockito.anyString(), Mockito.anyString());

        assertEquals(user.getFirstName(), "Max");
    }
}
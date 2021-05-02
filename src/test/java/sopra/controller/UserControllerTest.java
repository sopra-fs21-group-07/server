package sopra.controller;

/*import sopra.appuser.AppUserController;
import sopra.appuser.AppUserStatus;
import sopra.appuser.AppUser;
import sopra.appuser.AppUserService;*/

/**
 * AppUserControllerTest
 * This is a WebMvcTest which allows to test the AppUserController i.e. GET/POST request without actually sending them over the network.
 * This tests if the AppUserController works.
 */
/*@WebMvcTest(AppUserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppUserService AppUserService;

    @Test
    public void givenAppUsers_whenGetAppUsers_thenReturnJsonArray() throws Exception {
        // given
        AppUser AppUser = new AppUser();
        AppUser.setName("Firstname Lastname");
        AppUser.setAppUsername("firstname@lastname");
        AppUser.setStatus(AppUserStatus.OFFLINE);

        List<AppUser> allAppUsers = Collections.singletonList(AppUser);

        // this mocks the AppUserService -> we define above what the AppUserService should return when getAppUsers() is called
        given(AppUserService.getAppUsers()).willReturn(allAppUsers);

        // when
        MockHttpServletRequestBuilder getRequest = get("/AppUsers").contentType(MediaType.APPLICATION_JSON);

        // then
        mockMvc.perform(getRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(AppUser.getName())))
                .andExpect(jsonPath("$[0].AppUsername", is(AppUser.getAppUsername())))
                .andExpect(jsonPath("$[0].status", is(AppUser.getStatus().toString())));
    }}

    /*@Test
    public void createAppUser_validInput_AppUserCreated() throws Exception {
        // given
        AppUser AppUser = new AppUser();
        AppUser.setId(1L);
        AppUser.setName("Test AppUser");
        AppUser.setAppUsername("testAppUsername");
        AppUser.setToken("1");
        AppUser.setStatus(AppUserStatus.ONLINE);

        AppUserPostDTO AppUserPostDTO = new AppUserPostDTO();
        AppUserPostDTO.setName("Test AppUser");
        AppUserPostDTO.setAppUsername("testAppUsername");

        given(AppUserService.createAppUser(Mockito.any())).willReturn(AppUser);

        // when/then -> do the request + validate the result
        MockHttpServletRequestBuilder postRequest = post("/AppUsers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(AppUserPostDTO));

        // then
        mockMvc.perform(postRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(AppUser.getId().intValue())))
                .andExpect(jsonPath("$.name", is(AppUser.getName())))
                .andExpect(jsonPath("$.AppUsername", is(AppUser.getAppUsername())))
                .andExpect(jsonPath("$.status", is(AppUser.getStatus().toString())));
    }*/

    /**
     * Helper Method to convert AppUserPostDTO into a JSON string such that the input can be processed
     * Input will look like this: {"name": "Test AppUser", "AppUsername": "testAppUsername"}
     * @param object
     * @return string
     */
    /*private String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        }
        catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("The request body could not be created.%s", e.toString()));
        }
    }
}*/
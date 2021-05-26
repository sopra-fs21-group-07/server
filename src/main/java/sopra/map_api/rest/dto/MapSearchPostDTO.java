package sopra.map_api.rest.dto;

/**
 * Converter class from frontend to backend
 * @Autor: Beat Furrer
 */
public class MapSearchPostDTO {
    private String userInput;

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
}

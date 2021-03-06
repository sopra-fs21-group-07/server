package sopra.map_api.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import sopra.map_api.entity.Summit;

import java.util.ArrayList;
import java.util.List;

/**
 * This class get a UserInput (search for a summit) and returns the summit information from the geo.admin.map API
 * Example API request to the geo.admin.ch REST API
 * https://api3.geo.admin.ch/rest/services/api/MapServer/find?layer=ch.swisstopo.vec200-names-namedlocation&searchText=Gemsstock&searchField=objname1
 * @autor: Beat Furrer
 */
public class MapApiService {

    /**
     * Returns a List of possible summits which the user can choose in the frontend
     * @param userInput: String input with at least 3 char
     * @return List of possible summits or place names including the altitute and the coordinate
     */
    public List<Summit> getSummitInformation(String userInput)  {

        var apiConverter = new ApiConverter(userInput);
        final String uri = apiConverter.getUri();
        String[] result = new String[0];

        var restTemplate = new RestTemplate();
        var apiResponse = restTemplate.getForObject(uri, String.class);
        // Check on nullpointer exception
        if (apiResponse != null) {
            result = replaceUmlaute(apiResponse.split("\""));
        }
        return extractSummit(result);
    }

    public void updateGistGithub(String contentKML) throws Exception {
        final String uri = "https://api.github.com/gists/d0367bda086c97514a541ebd1911ba38?_method=patch";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = restTemplate.headForHeaders(uri);
        try{
            headers.set("Authorization", "token " + System.getenv("GIST_TOKEN"));
        } catch (Exception error){
            throw new Exception("Environment variable not found! Please add it to your local machine. Name: GIST_TOKEN");
        }
        String body = "{\"files\": {\"sopra07\": {\"content\": \""+contentKML+"\"}}}";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        restTemplate.postForObject(uri, entity, String.class);
    }

    public int[] getSummitCoordinates(String summitName, int altitude){
        int[]  coordinate = new int[2];
        String baseErrorMessage = "%s. Therefore, the Tour could not be created!";
        for (Summit el : getSummitInformation(summitName)) {
            if (el.getAltitude() == altitude) {
                coordinate[0] = el.getX();
                coordinate[1] = el.getY();
            }
        }
        if (coordinate[0] == 0 || coordinate[1] == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(baseErrorMessage, "Summit not found"));
        return coordinate;
    }

    /**
     * Extract from the API response the summit name, altitute and x & y coordinates.
     * The input is an String array which is splitted up in every fieldName of the API response.
     * @param result :
     * @return
     */
    private List<Summit> extractSummit(String[] result) {
        ArrayList<Summit> result2 = new ArrayList<>();
        Summit summit = new Summit();
        for (int i = 0; i < result.length; i++) {
            if (result[i].equals("y")){
                int y = trimToNumber(result[i+1]);
                int x = trimToNumber(result[i+3]);
                summit.setX(x);
                summit.setY(y);
            }
            if (result[i].equals("altitude")){
                int altitude = trimToNumber(result[i+1]);
                summit.setAltitude(altitude);
                summit.setName(result[i+4]);
                result2.add(summit);
                summit = new Summit();
            }
        }
        return result2;
    }

    /**
     * Replace all swiss ??, ?? and ?? and some other in lower and upper case.
     * @param input Raw data
     * @return String array whit special char
     */
    private String[] replaceUmlaute(String[] input){
        String[] output = input;
        for (int i = 0; i < output.length; i++){
            output[i] = output[i].replace("\\u00fc", "??")
                                 .replace("\\u00dc", "??")
                                 .replace("\\u00f6", "??")
                                 .replace("\\u00d6", "??")
                                 .replace("\\u00e4", "??")
                                 .replace("\\u00c4", "??")
                                 .replace("\\u00df", "ss")
                                 .replace("\\u00e9", "??")
                                 .replace("\\u00e8", "??")
                                 .replace("\\u00e0", "??")
                                 .replace("\\u00e2", "??")
                                 .replace("\\u00ea", "??")
                                 .replace("\\u00f4", "??");
        }
        return output;
    }

    /**
     * Convert an API response of a string to a number, replace space, double dot and comma
     * @param element type of ": 7854"
     * @return int number
     */
    private int trimToNumber(String element){
        return (int)Float.parseFloat(element
                .replace(":", "")
                .replace(",", "")
                .trim());
    }
}

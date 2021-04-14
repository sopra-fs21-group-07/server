package sopra.mapAPI.service;

import org.springframework.web.client.RestTemplate;
import sopra.mapAPI.entity.Summit;

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

        ApiConverter apiConverter = new ApiConverter(userInput);
        final String uri = apiConverter.getUri();

        RestTemplate restTemplate = new RestTemplate();
        String[] result = replaceUmlaute(restTemplate.getForObject(uri, String.class).split("\""));

        return extractSummit(result);
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
        //System.out.println(apiObject);
        return result2;
    }

    /**
     * Replace all swiss ä, ö and ü and some other in lower and upper case.
     * @param input Raw data
     * @return String array whit special char
     */
    private String[] replaceUmlaute(String[] input){
        String[] output = input;
        for (int i = 0; i < output.length; i++){
            output[i] = output[i].replace("\\u00fc", "ü")
                                 .replace("\\u00dc", "Ü")
                                 .replace("\\u00f6", "ö")
                                 .replace("\\u00d6", "Ö")
                                 .replace("\\u00e4", "ä")
                                 .replace("\\u00c4", "Ä")
                                 .replace("\\u00df", "ss")
                                 .replace("\\u00e9", "é")
                                 .replace("\\u00e8", "è")
                                 .replace("\\u00e0", "à")
                                 .replace("\\u00e2", "â")
                                 .replace("\\u00ea", "ê")
                                 .replace("\\u00f4", "ô");
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

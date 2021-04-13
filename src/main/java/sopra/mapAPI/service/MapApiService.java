package sopra.mapAPI.service;

import org.springframework.web.client.RestTemplate;
import sopra.mapAPI.entity.Summit;

import java.util.ArrayList;
import java.util.List;


/**
 * @autor: Beat Furrer
 */
public class MapApiService {

    public List<Summit> getCoordinate(String userInput)  {

        ApiConverter apiConverter = new ApiConverter(userInput);
        final String uri = apiConverter.getUri();

        RestTemplate restTemplate = new RestTemplate();
        String[] result = restTemplate.getForObject(uri, String.class).split("\"");

        return extractSummit(result);
    }

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

    private int trimToNumber(String element){
        return (int)Float.parseFloat(element
                .replace(":", "")
                .replace(",", "")
                .trim());
    }
}

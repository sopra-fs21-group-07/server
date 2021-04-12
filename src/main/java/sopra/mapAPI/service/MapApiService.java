package sopra.mapAPI.service;

import org.springframework.web.client.RestTemplate;
import sopra.mapAPI.entity.Summit;

public class MapApiService {
    public Summit getKoordinate(){
        final String uri = "https://api3.geo.admin.ch/rest/services/api/MapServer/find?layer=ch.swisstopo.vec200-names-namedlocation&searchText=Matterhorn&searchField=objname1";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        Summit summit = new Summit();
        summit.setName(result);

        return summit;
    }
}

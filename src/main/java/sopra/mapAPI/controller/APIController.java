package sopra.mapAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sopra.mapAPI.entity.Summit;
import sopra.mapAPI.service.MapApiService;
import sopra.tour.entity.Tour;
import sopra.tour.rest.dto.TourPostDTO;
import sopra.tour.rest.mapper.DTOMapperTour;

@RestController
public class APIController {
    private MapApiService mapApiService;

    APIController() {
        MapApiService mapApiService = new MapApiService();
    }

    @GetMapping("/namegeomapadmin")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Summit getAllTours() {
        // fetch all tours in the internal representation
        Summit summit = mapApiService.getKoordinate();

        return summit;
    }

    @PostMapping("/namegeomapadmin")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Summit createTour(@RequestBody TourPostDTO tourPostDTO) {
        // convert API tour to internal representation
        Tour tourInput = DTOMapperTour.INSTANCE.convertTourPostDTOtoEntity(tourPostDTO);

        // create tour
        Tour createdTour = tourService.createTour(tourInput);

        // convert internal representation of tour back to API
        return DTOMapperTour.INSTANCE.convertEntityToTourGetDTO(createdTour);
    }

    public void getKoordinate(){
        final String uri = "https://api3.geo.admin.ch/rest/services/api/MapServer/find?layer=ch.swisstopo.vec200-names-namedlocation&searchText=Matterhorn&searchField=objname1";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
    }
}
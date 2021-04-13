package sopra.mapAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopra.mapAPI.entity.Summit;
import sopra.mapAPI.rest.dto.MapFoundGetDTO;
import sopra.mapAPI.rest.dto.MapSearchPostDTO;
import sopra.mapAPI.rest.mapper.DTOMapperMapAPI;
import sopra.mapAPI.service.MapApiService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class APIController {
    private MapApiService mapApiService = null;

    APIController() {
        mapApiService = new MapApiService();
    }

    @GetMapping("/nameGeoMapAdmin")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Summit> getAllTours() {
        // fetch all tours in the internal representation
        List<Summit> summit = mapApiService.getCoordinate("Gitschen");

        return summit;
    }

    @PostMapping("/nameGeoMapAdmin")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public List<MapFoundGetDTO> createTour(@RequestBody MapSearchPostDTO mapSearchPostDTO) {
        // Create List for return value
        List<MapFoundGetDTO> foundSummits = new ArrayList();
        // convert API tour to internal representation
        Summit userInput = DTOMapperMapAPI.INSTANCE.convertMapSearchToSearchText(mapSearchPostDTO);

        // create tour
        List<Summit> summits = mapApiService.getCoordinate(userInput.getName());

        for (Summit summit : summits){
            foundSummits.add(DTOMapperMapAPI.INSTANCE.convertEntityToTourGetDTO(summit));
        }

        // convert internal representation of tour back to API
        return foundSummits;
    }
}
package sopra.mapApi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopra.mapApi.entity.Summit;
import sopra.mapApi.rest.dto.MapFoundGetDTO;
import sopra.mapApi.rest.dto.MapSearchPostDTO;
import sopra.mapApi.rest.mapper.DTOMapperMapAPI;
import sopra.mapApi.service.MapApiService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {
    private MapApiService mapApiService = null;

    ApiController() {
        mapApiService = new MapApiService();
    }

    @GetMapping("/nameGeoMapAdmin")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Summit> getAllTours() {
        // fetch all tours in the internal representation
        return mapApiService.getSummitInformation("Gitschen");
    }

    @PostMapping("/nameGeoMapAdmin")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public List<MapFoundGetDTO> createTour(@RequestBody MapSearchPostDTO mapSearchPostDTO) {
        // Create List for return value
        ArrayList foundSummits = new ArrayList();
        // convert API tour to internal representation
        Summit userInput = DTOMapperMapAPI.INSTANCE.convertMapSearchToSearchText(mapSearchPostDTO);

        // create tour
        List<Summit> summits = mapApiService.getSummitInformation(userInput.getName());

        for (Summit summit : summits){
            foundSummits.add(DTOMapperMapAPI.INSTANCE.convertEntityToTourGetDTO(summit));
        }

        // convert internal representation of tour back to API
        return foundSummits;
    }
}
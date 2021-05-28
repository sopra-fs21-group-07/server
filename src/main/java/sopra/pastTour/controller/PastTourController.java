package sopra.pastTour.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopra.pastTour.entity.PastTour;
import sopra.pastTour.rest.dto.PastTourGetDTO;
import sopra.pastTour.rest.mapper.DTOMapperPastTour;
import sopra.pastTour.service.PastTourService;
import sopra.tour.rest.dto.TourGetDTO;
import sopra.tour.rest.mapper.DTOMapperTour;

import java.util.ArrayList;
import java.util.List;

/**
 * Tour Controller
 * This class is responsible for handling all REST request that are related to the tour.
 * The controller will receive the request and delegate the execution to the pastTourService and finally return the result.
 */
@RestController
public class PastTourController {
    private final PastTourService pastTourService;

    PastTourController(PastTourService pastTourService) {
        this.pastTourService = pastTourService;
    }

    @GetMapping("/pastTours")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<PastTourGetDTO> getAllTours() {
        // fetch all tours in the internal representation
        List<PastTour> pastTours = pastTourService.getPastTours();
        List<PastTourGetDTO> pastTourGetDTO = new ArrayList<>();

        // convert each tour to the API representation
        for (PastTour pastTour : pastTours) {
            pastTourGetDTO.add(DTOMapperPastTour.INSTANCE.convertEntityToTourGetDTO(pastTour));
        }
        return pastTourGetDTO;
    }

    @GetMapping("/pastTours/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public PastTourGetDTO getOneTour(@PathVariable String id) {
        // fetch all tours in the internal representation
        return DTOMapperPastTour.INSTANCE.convertEntityToTourGetDTO(pastTourService.getPastTourById(Long.parseLong(id)));
    }
}


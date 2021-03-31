package ch.uzh.ifi.hase.soprafs21.controller;

import ch.uzh.ifi.hase.soprafs21.entity.Tour;
import ch.uzh.ifi.hase.soprafs21.rest.dto.TourGetDTO;
import ch.uzh.ifi.hase.soprafs21.rest.dto.TourPostDTO;
import ch.uzh.ifi.hase.soprafs21.rest.mapper.DTOMapperTour;
import ch.uzh.ifi.hase.soprafs21.service.TourService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Tour Controller
 * This class is responsible for handling all REST request that are related to the tour.
 * The controller will receive the request and delegate the execution to the TourService and finally return the result.
 */
@RestController
public class TourController {
    private final TourService tourService;

    TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/tours")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<TourGetDTO> getAllTours() {
        // fetch all tours in the internal representation
        List<Tour> tours = tourService.getTours();
        List<TourGetDTO> tourGetDTOs = new ArrayList<>();

        // convert each tour to the API representation
        for (Tour tour : tours) {
            tourGetDTOs.add(DTOMapperTour.INSTANCE.convertEntityToTourGetDTO(tour));
        }
        return tourGetDTOs;
    }

    @PostMapping("/tours")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public TourGetDTO createTour(@RequestBody TourPostDTO tourPostDTO) {
        // convert API tour to internal representation
        Tour tourInput = DTOMapperTour.INSTANCE.convertTourPostDTOtoEntity(tourPostDTO);

        // create tour
        Tour createdTour = tourService.createTour(tourInput);

        // convert internal representation of tour back to API
        return DTOMapperTour.INSTANCE.convertEntityToTourGetDTO(createdTour);
    }
}

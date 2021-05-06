package sopra.tour.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopra.tour.entity.Tour;
import sopra.tour.rest.dto.TourGetDTO;
import sopra.tour.rest.dto.TourPostDTO;
import sopra.tour.rest.dto.TourPutDTO;
import sopra.tour.rest.mapper.DTOMapperTour;
import sopra.tour.service.TourService;

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

    @GetMapping("/tours/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public TourGetDTO getOneTour(@PathVariable String id) {
        // fetch all tours in the internal representation
        return DTOMapperTour.INSTANCE.convertEntityToTourGetDTO(tourService.getTourById(Long.parseLong(id)));
    }

    @PostMapping("/tours")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public TourGetDTO createTour(@RequestBody TourPostDTO tourPostDTO) throws Exception {
        // convert API tour to internal representation
        Tour tourInput = DTOMapperTour.INSTANCE.convertTourPostDTOtoEntity(tourPostDTO);

        // create tour
        Tour createdTour = tourService.createTour(tourInput);

        // convert internal representation of tour back to API
        return DTOMapperTour.INSTANCE.convertEntityToTourGetDTO(createdTour);
    }

    @PutMapping("/tours/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public TourGetDTO addMemberToTour(@PathVariable String id, @RequestBody TourPutDTO tourPutDTO){
        // convert API tour to internal representation
        Tour inputTour= DTOMapperTour.INSTANCE.convertTourPutDTOtoEntity(tourPutDTO);

        //Search with ID for the tour in the repository
        Tour addMemberTour = tourService.getTourById(Integer.parseInt(id));

        //Check whether there are empty slots and add them to the tour
        tourService.add(addMemberTour, inputTour);

        return DTOMapperTour.INSTANCE.convertEntityToTourGetDTO(addMemberTour);
    }

}


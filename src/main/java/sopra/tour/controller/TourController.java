package sopra.tour.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopra.tour.entity.Tour;
import sopra.tour.entity.TourMember;
import sopra.tour.rest.dto.TourGetDTO;
import sopra.tour.rest.dto.TourMembersGetDTO;
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
        var tourInput = DTOMapperTour.INSTANCE.convertTourPostDTOtoEntity(tourPostDTO);

        // create tour
        var createdTour = tourService.createTour(tourInput);

        // convert internal representation of tour back to API
        return DTOMapperTour.INSTANCE.convertEntityToTourGetDTO(createdTour);
    }

    @PutMapping("/tours/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public TourGetDTO addMemberToTour(@PathVariable String id, @RequestBody TourPutDTO tourPutDTO){
        // convert API tour to internal representation
        var inputTour= DTOMapperTour.INSTANCE.convertTourPutDTOtoEntity(tourPutDTO);

        //Search with ID for the tour in the repository
        String s = id.replace("\n", "");
        var addMemberTour = tourService.getTourById(Long.parseLong(s));

        //Check whether there are empty slots and add them to the tour
        tourService.add(addMemberTour, inputTour);

        return DTOMapperTour.INSTANCE.convertEntityToTourGetDTO(addMemberTour);
    }

    @GetMapping("/tourMembers")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<TourMembersGetDTO> getAllTourMembers() {
        // fetch all tours in the internal representation
        List<TourMember> tourMembers = tourService.getTourMembers();
        List<TourMembersGetDTO> tourMembersGetDTO = new ArrayList<>();

        // convert each tour to the API representation
        for (TourMember tourMember : tourMembers) {
            tourMembersGetDTO.add(DTOMapperTour.INSTANCE.convertEntityToTourMembersGetDTO(tourMember));
        }
        return tourMembersGetDTO;
    }

    @DeleteMapping("/tours/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTour(@PathVariable long id) {
        tourService.deleteTour(id);
    }

    @DeleteMapping("/tourMembers/{id}/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelTour(@PathVariable String username, @PathVariable long id) {
        tourService.cancelTour(id, username);
    }

}


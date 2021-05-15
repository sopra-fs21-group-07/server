package sopra.tour.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopra.tour.rest.dto.editRequests.PutDate;
import sopra.tour.rest.dto.editRequests.PutEmptySlots;
import sopra.tour.rest.dto.editRequests.PutName;
import sopra.tour.service.TourService;
import sopra.userauthentication.dto.editRequests.PutUsername;

@RestController
@RequestMapping("/edit")
@AllArgsConstructor
public class TourEditController {

    private TourService tourService;

    @PutMapping(path = "/name/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editName(@PathVariable long id, @RequestBody PutName putName){
        tourService.editName(id, putName.getName());
    }

    @PutMapping(path = "/emptySlots/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editEmptySlots(@PathVariable long id, @RequestBody PutEmptySlots putEmptySlots){
        tourService.editEmptySlots(id, putEmptySlots.getEmptySlots());
    }

    @PutMapping(path = "/date/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editDate(@PathVariable long id, @RequestBody PutDate putDate){
        tourService.editDate(id, putDate.getDate());
    }

}

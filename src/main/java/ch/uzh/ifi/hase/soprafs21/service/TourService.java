package ch.uzh.ifi.hase.soprafs21.service;

import ch.uzh.ifi.hase.soprafs21.entity.Tour;
import ch.uzh.ifi.hase.soprafs21.repository.TourRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

/**
 * Tour Service
 * This class is the "worker" and responsible for all functionality related to the Tour
 * (e.g., it creates, modifies, deletes, finds). The result will be passed back to the caller.
 */
@Service
@Transactional
public class TourService {

    private final Logger log = LoggerFactory.getLogger(TourService.class);

    private final TourRepository tourRepository;

    @Autowired
    public TourService(@Qualifier("tourRepository") TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    public List<Tour> getTours() {
        return this.tourRepository.findAll();
    }

    public Tour createTour(Tour newTour) {
        newTour.setToken(UUID.randomUUID().toString());

        checkIfTourExists(newTour);

        // saves the given entity but data is only persisted in the database once flush() is called
        newTour = tourRepository.save(newTour);
        tourRepository.flush();

        log.debug("Created Information for Tour: {}", newTour);
        return newTour;
    }

    /**
     * This is a helper method that will check the uniqueness criteria of the Tourname and the name
     * defined in the Tour entity. The method will do nothing if the input is unique and throw an error otherwise.
     *
     * @param TourToBeCreated
     * @throws org.springframework.web.server.ResponseStatusException
     * @see Tour
     */
    private void checkIfTourExists(Tour TourToBeCreated) {
        Tour TourByTourname = tourRepository.findByName(TourToBeCreated.getName());
        Tour TourByName = tourRepository.findByName(TourToBeCreated.getName());

        String baseErrorMessage = "The %s provided %s not unique. Therefore, the Tour could not be created!";
        if (TourByTourname != null && TourByName != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(baseErrorMessage, "tourname and the name", "are"));
        }
        else if (TourByTourname != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(baseErrorMessage, "tourname", "is"));
        }
        else if (TourByName != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(baseErrorMessage, "name", "is"));
        }
    }
}

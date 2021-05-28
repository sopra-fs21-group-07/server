package sopra.pastTour.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopra.pastTour.entity.PastTour;
import sopra.pastTour.repository.PastTourRepository;
import sopra.tour.entity.Tour;

import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Tour Service
 * This class is the "worker" and responsible for all functionality related to the Tour
 * (e.g., it creates, modifies, deletes, finds). The result will be passed back to the caller.
 */
@Service
@Transactional
public class PastTourService {

    private final Logger log = LoggerFactory.getLogger(PastTourService.class);

    private final PastTourRepository pastTourRepository;
    private String currentURL = null;

    public String getCurrentURL() {
        return currentURL;
    }

    public void setCurrentURL(String currentURL) {
        this.currentURL = currentURL;
    }

    // Every 30min check and clean the tour repo
    // (cron = sec, min, hour, day, month, weekday)
    @Scheduled(cron = "* */30 * * * ?")
    public void clearToursOlderThenToday() {
        LocalDate today = LocalDate.now();

    }

    @Autowired
    public PastTourService(@Qualifier("pastTourRepository") PastTourRepository pastTourRepository) {
        this.pastTourRepository = pastTourRepository;
    }

    public List<PastTour> getPastTours() {
        return this.pastTourRepository.findAll();
    }

    public PastTour createPastTour(PastTour pastTour) throws Exception {
        // saves the given entity but data is only persisted in the database once flush() is called
        pastTour = pastTourRepository.save(pastTour);
        pastTourRepository.flush();

        log.debug("Created Information for Tour: {}", pastTour);

        return pastTour;
    }

    public PastTour getPastTourById(long id) {
        Optional<PastTour> tour = pastTourRepository.findById(id);
        if (tour.isPresent())
            return tour.get();
        else
            return new PastTour();
    }

}


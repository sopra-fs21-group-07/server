package sopra.pastTour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sopra.pastTour.entity.PastTour;

@Repository("pastTourRepository")
public interface PastTourRepository extends JpaRepository<PastTour, Long> {
    PastTour findBySummit(String summit);
}
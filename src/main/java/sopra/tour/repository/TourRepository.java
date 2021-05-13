package sopra.tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sopra.tour.entity.Tour;

@Repository("tourRepository")
public interface TourRepository extends JpaRepository<Tour, Long> {
    Tour findByName(String name);
    Tour findById(long id);
    @Query("SELECT e FROM Tour e WHERE e.date < CURRENT_DATE")
    Iterable<Tour> findAllValid();
}

package ch.uzh.ifi.hase.soprafs21.repository;

import ch.uzh.ifi.hase.soprafs21.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tourRepository")
public interface TourRepository extends JpaRepository<Tour, Long>{
    Tour findByName(String name);

    Tour findById(String username);
}

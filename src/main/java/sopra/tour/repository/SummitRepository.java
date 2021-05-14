package sopra.tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sopra.tour.entity.Summit;

@Repository("summitRepository")
public interface SummitRepository extends JpaRepository<Summit, Long> {
    Summit findByName(String name);

    Summit findById(long id);
}

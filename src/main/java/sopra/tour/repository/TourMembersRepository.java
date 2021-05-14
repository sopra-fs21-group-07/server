package sopra.tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sopra.tour.entity.TourMember;

@Repository("tourMembersRepository")
public interface TourMembersRepository extends JpaRepository<TourMember, Long> {
    TourMember findByName(String name);
    TourMember findById(long id);
    TourMember findByTourName(String tourname);
}
package sopra.Post.Repo;

import sopra.Post.entity.Post;
import sopra.pastTour.entity.PastTour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sopra.userauthentication.model.User;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByPastTour(PastTour pastTour);

    List<Post> findByUser(User user);
}

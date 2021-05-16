package sopra.Post.mapper;

//import com.github.marlonlom.utilities.timeago.TimeAgo;
import sopra.Post.dto.PostRequest;
import sopra.Post.dto.PostResponse;
import sopra.Post.entity.*;
import sopra.Post.Repo.CommentRepository;
import sopra.Post.Repo.VoteRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import sopra.pastTour.entity.PastTour;
import sopra.userauthentication.model.User;
import sopra.userauthentication.service.AuthService;

import java.util.Optional;

import static sopra.Post.entity.VoteType.DOWNVOTE;
import static sopra.Post.entity.VoteType.UPVOTE;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;


    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "pastTour", source = "pastTour")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public abstract Post map(PostRequest postRequest, PastTour pastTour, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "postName", source = "pastTour.id")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    //@Mapping(target = "duration", expression = "java(getDuration(post))")
    @Mapping(target = "upVote", expression = "java(isPostUpVoted(post))")
    @Mapping(target = "downVote", expression = "java(isPostDownVoted(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    /*String getDuration(Post post) {
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }*/

    boolean isPostUpVoted(Post post) {
        return checkVoteType(post, UPVOTE);
    }

    boolean isPostDownVoted(Post post) {
        return checkVoteType(post, DOWNVOTE);
    }

    private boolean checkVoteType(Post post, VoteType voteType) {
        if (authService.isLoggedIn()) {
            Optional<Vote> voteForPostByUser =
                    voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post,
                            authService.getCurrentUser());
            return voteForPostByUser.filter(vote -> vote.getVoteType().equals(voteType))
                    .isPresent();
        }
        return false;
    }

}
package sopra.Post.service;

import sopra.Post.dto.PostRequest;
import sopra.Post.dto.PostResponse;
import sopra.Post.exceptions.PostNotFoundException;
import sopra.Post.exceptions.SubredditNotFoundException;
import sopra.Post.mapper.PostMapper;
import sopra.Post.entity.Post;
import sopra.pastTour.entity.PastTour;
import sopra.Post.Repo.PostRepository;
import sopra.pastTour.repository.PastTourRepository;
import sopra.userauthentication.model.User;
import sopra.userauthentication.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopra.userauthentication.service.AuthService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final PastTourRepository pastTourRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public void save(PostRequest postRequest) {
        PastTour pastTour = pastTourRepository.findBySummit(postRequest.getPasttourName());
                //.orElseThrow(() -> new SubredditNotFoundException(postRequest.getPasttourName()));
        postRepository.save(postMapper.map(postRequest, pastTour, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        PastTour pastTour = pastTourRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllByPastTour(pastTour);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}

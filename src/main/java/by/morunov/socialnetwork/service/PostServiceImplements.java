package by.morunov.socialnetwork.service;

import by.morunov.socialnetwork.model.Post;
import by.morunov.socialnetwork.model.User;
import by.morunov.socialnetwork.model.dto.PostDto;
import by.morunov.socialnetwork.repository.PostRepository;
import by.morunov.socialnetwork.util.PostDtoConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alex Morunov
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImplements{

    private final PostRepository postRepository;

    private final PostDtoConverter postDtoConverter;

    public void addPost(Post post) {
        postRepository.save(post);
    }


    public List<PostDto> getAllPostsByAuthor(User user) {
        return postDtoConverter.fromListOfPost(postRepository.findAllByAuthor(user));
    }

    public List<PostDto> getAllSubscribersPosts(User user) {
        return postDtoConverter.fromListOfPost(postRepository.findAllByAuthor(user));
    }
}

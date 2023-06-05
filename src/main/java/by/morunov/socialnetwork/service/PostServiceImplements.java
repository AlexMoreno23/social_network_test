package by.morunov.socialnetwork.service;

import by.morunov.socialnetwork.model.Post;
import by.morunov.socialnetwork.model.User;
import by.morunov.socialnetwork.model.dto.PostDto;
import by.morunov.socialnetwork.repository.PostRepository;
import by.morunov.socialnetwork.repository.UserRepo;
import by.morunov.socialnetwork.util.PostDtoConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alex Morunov
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImplements{

    private final UserRepo userRepo;

    private final PostRepository postRepository;

    private final PostDtoConverter postDtoConverter;

    public void addPost(Post post) {
        postRepository.save(post);
    }

    List<PostDto> findAllPostsByAuthorId(Long id){
        return postDtoConverter.fromListOfPost(postRepository.findAllPostsByAuthorId(id));
    }


    public List<PostDto> getAllPostsByAuthor(User user) {
        return postDtoConverter.fromListOfPost(postRepository.findAllByAuthor(user));
    }

    public List<List<PostDto>> getAllSubscribersPosts(Long id) {
        List<Long> subscribersId = userRepo.findAllSubscribersId(id);

        return subscribersId.stream().map(this:: findAllPostsByAuthorId).collect(Collectors.toList());
    }
}

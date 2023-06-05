package by.morunov.socialnetwork.util;

import by.morunov.socialnetwork.model.Post;
import by.morunov.socialnetwork.model.dto.PostDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alex Morunov
 */
@Component
public class PostDtoConverter {

    public PostDto fromPost(Post post){
        return PostDto.builder()
                .title(post.getTitle())
                .text(post.getText())
                .author(post.getAuthor().getName())
                .build();
    }

    public List<PostDto> fromListOfPost(List<Post> posts){
        return posts.stream().map(this::fromPost).collect(Collectors.toList());
    }

}

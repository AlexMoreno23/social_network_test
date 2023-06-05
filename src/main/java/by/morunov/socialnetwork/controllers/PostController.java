package by.morunov.socialnetwork.controllers;

import by.morunov.socialnetwork.model.Post;
import by.morunov.socialnetwork.model.User;
import by.morunov.socialnetwork.model.dto.PostDto;
import by.morunov.socialnetwork.service.PostServiceImplements;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Alex Morunov
 */
@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping("/api/v1/home/post")
@RequiredArgsConstructor
public class PostController {

    private final PostServiceImplements postService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(@AuthenticationPrincipal User author){
        List<PostDto> postDtos = postService.getAllPostsByAuthor(author);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> addPost(@AuthenticationPrincipal User user,
            @RequestBody Post post){
        post.setAuthor(user);
        postService.addPost(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

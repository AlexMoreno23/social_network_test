package by.morunov.socialnetwork.repository;

import by.morunov.socialnetwork.model.Post;
import by.morunov.socialnetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex Morunov
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByAuthor(User author);
}

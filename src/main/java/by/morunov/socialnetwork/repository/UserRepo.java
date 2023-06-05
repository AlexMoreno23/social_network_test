package by.morunov.socialnetwork.repository;

import by.morunov.socialnetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Alex Morunov
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = "SELECT to_id FROM _invite WHERE from_id = ?1", nativeQuery = true)
    List<Long> findAllSubscribersId(Long id);

    @Query(value = "SELECT friends_id FROM _user_friends WHERE user_id = ?1", nativeQuery = true)
    List<Long> findAllFriends(Long id);
    Optional<User> findByEmail(String email);

    List<User> findAllByName(String name);
}

package by.morunov.socialnetwork.repository;

import by.morunov.socialnetwork.model.Invite;
import by.morunov.socialnetwork.model.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Alex Morunov
 */
@Repository
public interface InviteRepo extends JpaRepository<Invite, Long> {

    Optional<Invite> findById(@NonNull Long id);
    Optional<List<Invite>> findAllByFrom(User user);

    Optional<List<Invite>> findAllByTo(User user);
}

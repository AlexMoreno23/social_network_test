package by.morunov.socialnetwork.service;

import by.morunov.socialnetwork.model.Invite;
import by.morunov.socialnetwork.model.InviteStatus;
import by.morunov.socialnetwork.model.User;
import by.morunov.socialnetwork.model.dto.InviteDto;
import by.morunov.socialnetwork.model.dto.UserDto;
import by.morunov.socialnetwork.repository.InviteRepo;
import by.morunov.socialnetwork.repository.UserRepo;
import by.morunov.socialnetwork.util.InviteDtoConverter;
import by.morunov.socialnetwork.util.UserDTOConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alex Morunov
 */
@Service
@RequiredArgsConstructor
public class UserServiceImplements {

    private final UserRepo userRepo;

    private final InviteRepo inviteRepo;

    private final UserDTOConverter userDTOConverter;

    private final InviteDtoConverter inviteDtoConverter;

    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow();
    }


    public List<UserDto> userListByName(String name) {
        return userDTOConverter.fromListOfUsers(userRepo.findAllByName(name));
    }

    public List<UserDto> allUsers() {
        return userDTOConverter.fromListOfUsers(userRepo.findAll());
    }


    public void sendInvite(Long from, Long id) {
        inviteRepo.save(Invite.builder()
                .from(userRepo.findById(from).orElseThrow())
                .to(userRepo.findById(id).orElseThrow())
                .status(InviteStatus.IN_WAITING)
                .build());
    }

    public List<InviteDto> invitesFromUser(User user) {
        return inviteDtoConverter.toInviteDtoList(inviteRepo.findAllByFrom(user).orElseThrow());
    }

    public List<InviteDto> invitesToUser(User user) {
        return inviteDtoConverter.toInviteDtoList(inviteRepo.findAllByTo(user).orElseThrow());
    }

    public void responseToInvite(Long inviteId, InviteStatus inviteStatus) {
        Invite updateInvite = inviteRepo.findById(inviteId).orElseThrow();
        updateInvite.setStatus(inviteStatus);
        inviteRepo.save(updateInvite);

        if (updateInvite.getStatus().equals(InviteStatus.ACCEPTED)) {
            User user1 = userRepo.findById(updateInvite.getFrom().getId()).orElseThrow();
            user1.getFriends().add(userRepo.findById(updateInvite.getTo().getId()).orElseThrow());
            User user2 = userRepo.findById(updateInvite.getTo().getId()).orElseThrow();
            user2.getFriends().add(userRepo.findById(updateInvite.getFrom().getId()).orElseThrow());
            userRepo.save(user1);
            userRepo.save(user2);
        }

    }

    public List<UserDto> getAllFriends(Long id) {
        List<Long> allFriendsId = userRepo.findAllFriends(id);
        return userDTOConverter.fromListOfUsers(allFriendsId.stream()
                .map(this::findById).collect(Collectors.toList()));
    }

    public List<UserDto> getAllSubscribers(Long id){
        List<Long> allSubscribersId = userRepo.findAllSubscribersId(id);

        return userDTOConverter.fromListOfUsers(allSubscribersId.stream()
                .map(this::findById).collect(Collectors.toList()));
    }


}

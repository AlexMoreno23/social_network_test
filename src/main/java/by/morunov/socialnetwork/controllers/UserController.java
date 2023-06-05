package by.morunov.socialnetwork.controllers;

import by.morunov.socialnetwork.model.Invite;
import by.morunov.socialnetwork.model.InviteStatus;
import by.morunov.socialnetwork.model.User;
import by.morunov.socialnetwork.model.dto.InviteDto;
import by.morunov.socialnetwork.model.dto.UserDto;
import by.morunov.socialnetwork.service.UserServiceImplements;
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
@RequestMapping("/api/v1/home")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImplements userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> allUsers() {
        List<UserDto> allUsers = userService.allUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<UserDto>> allUsersByUsername(@PathVariable("name") String name) {

        return new ResponseEntity<>(userService.userListByName(name),
                HttpStatus.OK);
    }

    @PostMapping("/users/invite/send/{id}")
    public ResponseEntity<Invite> sendInvite(@PathVariable("id") Long id,
                                             @AuthenticationPrincipal User user) {
        userService.sendInvite(user.getId(), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users/my-invites")
    public ResponseEntity<List<InviteDto>> myInvites(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(userService.invitesFromUser(user),
                HttpStatus.OK);
    }

    @GetMapping("/users/to-me-invites")
    public ResponseEntity<List<InviteDto>> toMeInvites(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(userService.invitesToUser(user),
                HttpStatus.OK);
    }

    @PostMapping("/users/to-me-invites/reject/{idInvite}")
    public ResponseEntity<HttpStatus> rejectInvite(@PathVariable("idInvite") Long id){
        userService.responseToInvite(id, InviteStatus.REJECTED);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PostMapping("/users/to-me-invites/accept/{idInvite}")
    public ResponseEntity<HttpStatus> acceptInvite(@PathVariable("idInvite") Long id){
        userService.responseToInvite(id, InviteStatus.ACCEPTED);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/friends")
    public ResponseEntity<List<UserDto>> getAllFriends(@AuthenticationPrincipal User user){
        return new ResponseEntity<>(userService.getAllFriends(user.getId()), HttpStatus.OK);
    }

    @GetMapping("/subscribers")
    public ResponseEntity<List<UserDto>> getAllSubscribers(@AuthenticationPrincipal User user){
        return new ResponseEntity<>(userService.getAllSubscribers(user.getId()), HttpStatus.OK);
    }
}

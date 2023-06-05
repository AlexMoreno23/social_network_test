package by.morunov.socialnetwork.util;

import by.morunov.socialnetwork.model.User;
import by.morunov.socialnetwork.model.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alex Morunov
 */
@Component
public class UserDTOConverter {

    public UserDto fromUser(User user){
        return UserDto.builder()
               .id(user.getId())
                .name(user.getName())
               .email(user.getEmail())
               .role(user.getRole())
               .build();
    }

    public List<UserDto> fromListOfUsers(List<User> users){
        return users.stream().map(this::fromUser).collect(Collectors.toList());
    }
}

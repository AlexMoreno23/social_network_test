package by.morunov.socialnetwork.util;

import by.morunov.socialnetwork.model.Invite;
import by.morunov.socialnetwork.model.dto.InviteDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alex Morunov
 */
@Component
public class InviteDtoConverter {

    public InviteDto toInviteDto(Invite invite){
        return InviteDto.builder()
                .id(invite.getId())
                .from(invite.getFrom().getName())
                .to(invite.getTo().getName())
                .status(invite.getStatus())
                .build();
    }

    public List<InviteDto> toInviteDtoList(List<Invite> invites){
        return invites.stream().map(this::toInviteDto).collect(Collectors.toList());
    }

}

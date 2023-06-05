package by.morunov.socialnetwork.model.dto;

import by.morunov.socialnetwork.model.InviteStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alex Morunov
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InviteDto {

    private Long id;

    private String from;

    private String to;

    @Enumerated(EnumType.STRING)
    private InviteStatus status;
}

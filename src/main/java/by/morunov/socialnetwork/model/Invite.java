package by.morunov.socialnetwork.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alex Morunov
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_invite")
public class Invite {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User from;

    @OneToOne
    private User to;

    @Enumerated(EnumType.STRING)
    private InviteStatus status;


}

package by.morunov.socialnetwork.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Alex Morunov
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    private User author;
}

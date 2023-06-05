package by.morunov.socialnetwork.model.dto;

import by.morunov.socialnetwork.model.User;
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
public class PostDto {

    private String title;

    private String text;

    private String author;
}

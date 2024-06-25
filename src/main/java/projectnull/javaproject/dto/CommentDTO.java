package projectnull.javaproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
    private String uuid = null;
    private String post;
    private String content;
    private String writer = null;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}

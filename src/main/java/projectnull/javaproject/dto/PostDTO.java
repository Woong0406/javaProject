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
public class PostDTO {
    private String uuid = null;
    private String title;
    private String content;
    private String writer = null;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}

package projectnull.javaproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID uuid = UUID.randomUUID();
    private String content;
    @ManyToOne
    private Post post;
    @ManyToOne
    private User writer;
}

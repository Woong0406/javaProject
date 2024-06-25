package projectnull.javaproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "post")
public class Post extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID uuid = UUID.randomUUID();
    private String title;
    @Column(length = 10000)
    private String content;
    @ManyToOne
    private User writer;
}

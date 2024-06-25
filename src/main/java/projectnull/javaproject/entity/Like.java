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
@Table(name = "post_like")
public class Like extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID uuid = UUID.randomUUID();
    @ManyToOne
    private Post post;
    @ManyToOne
    private User user;
}

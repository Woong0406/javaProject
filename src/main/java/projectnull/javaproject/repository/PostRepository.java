package projectnull.javaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectnull.javaproject.entity.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    public Optional<Post> findPostByUuid(UUID uuid);
}

package projectnull.javaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectnull.javaproject.entity.Like;
import projectnull.javaproject.entity.Post;
import projectnull.javaproject.entity.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LikeRepository extends JpaRepository<Like, UUID> {
    Optional<Like> findLikeByUserAndPost(User user, Post post);
    Boolean existsLikeByUserAndPost(User user, Post post);
    Integer countLikesByPost(Post post);
}

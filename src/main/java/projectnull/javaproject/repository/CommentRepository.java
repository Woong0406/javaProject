package projectnull.javaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectnull.javaproject.entity.Comment;
import projectnull.javaproject.entity.Post;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findCommentsByPost(Post post);
}

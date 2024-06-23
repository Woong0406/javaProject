package projectnull.javaproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import projectnull.javaproject.dto.CommentDTO;
import projectnull.javaproject.dto.CustomUserDetails;
import projectnull.javaproject.entity.Comment;
import projectnull.javaproject.entity.Post;
import projectnull.javaproject.repository.CommentRepository;
import projectnull.javaproject.repository.PostRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    public List<CommentDTO> getComments(String postUUID) {
        Optional<Post> post = postRepository.findPostByUuid(UUID.fromString(postUUID));
        if (post.isPresent()) {
            List<Comment> comments = commentRepository.findCommentsByPost(post.get());
            return comments.stream().map((it)->CommentDTO.builder().uuid(it.getUuid().toString()).writer(it.getWriter().getNickName()).content(it.getContent()).regDate(it.getRegDate()).modDate(it.getModDate()).build()).collect(Collectors.toList());
        }
        return null;
    }
    public Comment postComment(CommentDTO dto) {
        // todo 수정
        return commentRepository.save(new Comment(null ,dto.getContent(), postRepository.findPostByUuid(UUID.fromString(dto.getPost())).get(), ((CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser()));
    }
}

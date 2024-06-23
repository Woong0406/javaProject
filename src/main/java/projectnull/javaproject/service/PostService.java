package projectnull.javaproject.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import projectnull.javaproject.dto.CustomUserDetails;
import projectnull.javaproject.dto.PostDTO;
import projectnull.javaproject.entity.Post;
import projectnull.javaproject.entity.User;
import projectnull.javaproject.repository.PostRepository;
import projectnull.javaproject.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public Post postPost(PostDTO dto) {
        if (dto.getUuid() != null) {
            Optional<Post> post = postRepository.findPostByUuid(UUID.fromString(dto.getUuid()));
            if (post.isPresent()) {
                if (((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getUuid().equals(post.get().getWriter().getUuid())) {
                    post.get().setTitle(dto.getTitle());
                    post.get().setContent(dto.getContent());
                }
            }

            return postRepository.save(post.get());
        }
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //post 엔티티를 만들어서 저장시키는 로직
        Post post = Post.builder()
                .content(dto.getContent())
                .title(dto.getTitle())
                .writer(userDetails.getUser())
                .build();
        return postRepository.save(post);
    }
    public PostDTO getPostInfo(String postUUID) {
        Optional<Post> post = postRepository.findPostByUuid(UUID.fromString(postUUID));
        if (post.isPresent()) {
            return PostDTO.builder()
                    .uuid(post.get().getUuid().toString())
                    .title(post.get().getTitle())
                    .content(post.get().getContent())
                    .writer(post.get().getWriter().getNickName())
                    .regDate(post.get().getRegDate())
                    .modDate(post.get().getModDate())
                    .build();
        }
        return null;
    }
    public List<PostDTO> getPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map((it)->
            PostDTO.builder().title(it.getTitle()).writer(it.getWriter().getNickName()).uuid(it.getUuid().toString()).build()
        ).collect(Collectors.toList());
    }
    public void deletePost(String postUUID) {
        postRepository.delete(postRepository.findPostByUuid(UUID.fromString(postUUID)).get());
    }
}

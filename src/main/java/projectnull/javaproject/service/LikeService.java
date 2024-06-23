package projectnull.javaproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import projectnull.javaproject.dto.CustomUserDetails;
import projectnull.javaproject.entity.Like;
import projectnull.javaproject.repository.LikeRepository;
import projectnull.javaproject.repository.PostRepository;
import projectnull.javaproject.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private PostRepository postRepository;
    public Like postLike(String postUUID) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Like> like = likeRepository.findLikeByUserAndPost(userDetails.getUser(), postRepository.findPostByUuid(UUID.fromString(postUUID)).get());
        if (like.isPresent()) {
            likeRepository.delete(like.get());
            return null;
        } else {
            return likeRepository.save(Like.builder().post(postRepository.findPostByUuid(UUID.fromString(postUUID)).get()).user(((CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser()).build());
        }
    }
    public Boolean isLiked(String postUUID) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return likeRepository.existsLikeByUserAndPost(userDetails.getUser(), postRepository.findPostByUuid(UUID.fromString(postUUID)).get());
    }
    public Integer getLikes(String postUUID) {
        return likeRepository.countLikesByPost(postRepository.findPostByUuid(UUID.fromString(postUUID)).get());
    }
}

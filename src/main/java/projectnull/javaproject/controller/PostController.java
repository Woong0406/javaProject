package projectnull.javaproject.controller;
//히히 주석
//히히 또 주석
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import projectnull.javaproject.dto.CustomUserDetails;
import projectnull.javaproject.dto.PostDTO;
import projectnull.javaproject.service.CommentService;
import projectnull.javaproject.service.LikeService;
import projectnull.javaproject.service.PostService;

@Controller
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private LikeService likeService;

    @GetMapping("/post")
    public String getPost(Model model) {
        model.addAttribute("title", "");
        model.addAttribute("content", "");
        return "post";
    }

    @PostMapping("/post")
    public String postPost(PostDTO dto) {
        postService.postPost(dto);
        return "redirect:/";
    }
    @GetMapping("/post/{postUUID}")
    public String getPostInfo(@PathVariable String postUUID, Model model, @RequestParam(required = false, defaultValue = "false") Boolean modify) {
        PostDTO postDTO = postService.getPostInfo(postUUID);
        if (modify) {
            model.addAttribute("title", postDTO.getTitle());
            model.addAttribute("content", postDTO.getContent());
            model.addAttribute("uuid", postDTO.getUuid());
            return "postModify";
        }
        if (postDTO != null) {
            // todo 나중에 수정하세요// todo 나중에 수정하세요// todo 나중에 수정하세요// todo 나중에 수정하세요// todo 나중에 수정하세요
            model.addAttribute("title", postDTO.getTitle());
            model.addAttribute("content", postDTO.getContent());
            model.addAttribute("writer", postDTO.getWriter());
            model.addAttribute("regData", postDTO.getRegDate());
            model.addAttribute("modData", postDTO.getModDate());
            model.addAttribute("comments", commentService.getComments(postUUID));

            model.addAttribute("likes",likeService.getLikes(postDTO.getUuid()));
            // todo 나중에 수정하세요
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            boolean isAuthenticated = authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken);

            if (isAuthenticated) {
                CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
                model.addAttribute("isWriter", userDetails.getUser().getNickName().equals(postDTO.getWriter()));
                model.addAttribute("isLoginned", true);
                model.addAttribute("isLiked", likeService.isLiked(postDTO.getUuid()));
            } else {
                model.addAttribute("isWriter", false);
                model.addAttribute("isLoginned", false);
                model.addAttribute("isLiked", false);
            }
            return "postInfo";
        }
        return "redirect:/";
    }
    @DeleteMapping("/post/{postUUID}")
    public String deletePost(@PathVariable String postUUID) {
        postService.deletePost(postUUID);
        return "redirect:/";
    }
}

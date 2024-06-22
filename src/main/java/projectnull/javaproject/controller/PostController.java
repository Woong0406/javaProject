package projectnull.javaproject.controller;
//히히 주석
//히히 또 주석
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import projectnull.javaproject.dto.PostDTO;
import projectnull.javaproject.service.PostService;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/post")
    public String getPost() {
        return "post";
    }

    @PostMapping("/post")
    public String postPost(PostDTO dto) {
        postService.postPost(dto);
        return "redirect:/";
    }
    @GetMapping("/post/{postUUID}")
    public String getPostInfo(@PathVariable String postUUID, Model model) {
        PostDTO postDTO = postService.getPostInfo(postUUID);
        if (postDTO != null) {
            model.addAttribute("title", postDTO.getTitle());
            model.addAttribute("content", postDTO.getContent());
            model.addAttribute("writer", postDTO.getWriter());
            return "postInfo";
        }
        return "redirect:/";
    }
}

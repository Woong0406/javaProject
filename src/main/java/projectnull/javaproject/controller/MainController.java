package projectnull.javaproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import projectnull.javaproject.service.PostService;

@Controller
public class MainController {
    @Autowired
    private PostService postService;
    @GetMapping("/")
    public String getMain(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken);
        model.addAttribute("posts", postService.getPosts());
        model.addAttribute("isLoginned", isAuthenticated);
        return "main";
    }

}

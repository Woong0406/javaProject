package projectnull.javaproject.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String getMain(Model model) {
        model.addAttribute("nickName", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return "main";
    }
    @GetMapping("/post")
    public String getPost() {
        return "post";
    }
}

package projectnull.javaproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import projectnull.javaproject.service.LikeService;

@Controller
public class LikeController {
    @Autowired
    private LikeService likeService;
    @GetMapping("/like/{postUUID}")
    public String postLike(@PathVariable String postUUID) {
        likeService.postLike(postUUID);
        return "redirect:/post/" + postUUID;
    }
}

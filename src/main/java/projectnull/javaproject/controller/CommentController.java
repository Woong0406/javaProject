package projectnull.javaproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import projectnull.javaproject.dto.CommentDTO;
import projectnull.javaproject.service.CommentService;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/comment/{postUUID}")
    public String postComment(CommentDTO dto, @PathVariable String postUUID) {
        dto.setPost(postUUID);
        commentService.postComment(dto);
        return "redirect:/post/" + postUUID;
    }

}

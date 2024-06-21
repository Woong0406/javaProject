package projectnull.javaproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import projectnull.javaproject.dto.SignUpDTO;
import projectnull.javaproject.service.AuthService;

import java.io.IOException;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
    @GetMapping("/sign-up")
    public String getSignUp() {
        return "sign-up";
    }
    @PostMapping("/sign-up")
    public String postSignUp(SignUpDTO dto, Model model) throws IOException {
        Boolean result = authService.signUp(dto);
        if (result) {
            return "redirect:/login";
        }
        return null;
    }
}

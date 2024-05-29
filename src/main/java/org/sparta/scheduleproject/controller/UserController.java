package org.sparta.scheduleproject.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.sparta.scheduleproject.dto.LoginRequestDto;
import org.sparta.scheduleproject.dto.SignupRequestDto;
import org.sparta.scheduleproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/login-page")
    public String loginPage() {
        return "redirect:/";
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/user/signup")
    public String signup(@RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);

        return "/user/login-page";
    }

    @PostMapping("/user/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse res){
        try {
            userService.login(loginRequestDto,res);
        } catch (Exception e) {
            e.getMessage();
        }
        return "redirect:/user/login-page";
    }
}

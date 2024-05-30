package org.sparta.scheduleproject.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.sparta.scheduleproject.dto.LoginRequestDto;
import org.sparta.scheduleproject.dto.LoginResponseDto;
import org.sparta.scheduleproject.dto.SignupRequestDto;
import org.sparta.scheduleproject.dto.SignupResponseDto;
import org.sparta.scheduleproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/*
1.강의에서는 시큐리티? 로그인을 먼저하고 로그인,회원가입 진행했지만 여기서는 모르겠음

 */
@Controller
@RequestMapping("/api")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/signup")
    public SignupResponseDto signup(@RequestBody  SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);

    }

    @PostMapping("/user/login")
    public @ResponseBody LoginResponseDto login(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpServletResponse res){
        try {
            userService.login(loginRequestDto,res);
        } catch (Exception e) {
            e.getMessage();
        }
        return userService.login(loginRequestDto,res);
    }
}

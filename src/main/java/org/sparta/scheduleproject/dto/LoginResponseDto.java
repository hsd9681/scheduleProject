package org.sparta.scheduleproject.dto;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.sparta.scheduleproject.entity.User;

@Getter
public class LoginResponseDto {
    private String username;
    private String password;



    public LoginResponseDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

}
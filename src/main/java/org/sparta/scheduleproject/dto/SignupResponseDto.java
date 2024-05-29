package org.sparta.scheduleproject.dto;

import org.sparta.scheduleproject.entity.User;

public class SignupResponseDto {
    private String username;
    private String password;



    public SignupResponseDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

}

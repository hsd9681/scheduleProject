package org.sparta.scheduleproject.dto;

import lombok.Getter;
import org.sparta.scheduleproject.entity.User;

@Getter
public class SignupResponseDto {
    private String username;
    private String password;



    public SignupResponseDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

}

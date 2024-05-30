package org.sparta.scheduleproject.dto;


import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "4자에서 10자의 알파벳 소문자와 숫자로 구성되어야 합니다.")
    private String username;
    @Pattern(regexp = "^[a-zA-z0-9]{8,15}$", message = "8자에서 15자의 알파벳 대소문자와 숫자로 구성되어야 합니다.")
    private String password;
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}
package org.sparta.scheduleproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sparta.scheduleproject.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleRequestDto extends User {

    private String title;
    @NotBlank
    private String contents;
    @NotBlank
    private String username; //-->담당자
    @NotBlank
    private String password;

//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    String loggedInUsername = authentication.getName();
//
//    public ScheduleRequestDto(String title, String contents,String password) {
//        this.username = title;
//        this.password = contents;
//        this.title = loggedInUsername;
//        this.contents = password;
//    }
}

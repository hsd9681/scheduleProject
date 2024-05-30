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
public class ScheduleRequestDto {

    private String title;
    @NotBlank
    private String contents;
    @NotBlank
    private String username; //-->담당자
    @NotBlank
    private String password;

}

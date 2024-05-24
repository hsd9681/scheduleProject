package org.sparta.scheduleproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleRequestDto {

    private String title;
    @NotBlank
    private String contents;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}

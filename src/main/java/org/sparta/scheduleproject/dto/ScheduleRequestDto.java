package org.sparta.scheduleproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleRequestDto {

    @Email(message = "??????")
    private String title;
    @NotBlank
    private String contents;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}

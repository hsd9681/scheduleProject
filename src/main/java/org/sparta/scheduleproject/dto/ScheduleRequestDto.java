package org.sparta.scheduleproject.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleRequestDto {

    private String title;
    private String contents;
    private String username;
    private String password;
}

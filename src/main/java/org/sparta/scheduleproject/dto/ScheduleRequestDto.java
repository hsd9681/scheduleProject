package org.sparta.scheduleproject.dto;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ScheduleRequestDto {

    private String title;
    private String contents;
    private String username;
    private Long password;
}

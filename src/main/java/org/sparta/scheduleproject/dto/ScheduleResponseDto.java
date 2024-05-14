package org.sparta.scheduleproject.dto;

import lombok.Getter;
import org.sparta.scheduleproject.entity.Schedule;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String username;
    private String contents;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.username = schedule.getUsername();
        this.contents = schedule.getContents();

    }

    public ScheduleResponseDto(Long id, String title, String username, String contents ) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.contents = contents;
    }
}

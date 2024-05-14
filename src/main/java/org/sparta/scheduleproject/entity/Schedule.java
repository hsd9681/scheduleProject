package org.sparta.scheduleproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sparta.scheduleproject.dto.ScheduleRequestDto;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class Schedule extends Timestamped {
    private Long id;
    private String title;
    private String contents;
    private String username;
    private Long password;
    private LocalDateTime createdAt;



    public Schedule(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
        this.createdAt = LocalDateTime.now();

    }

    public void selectschedule(Schedule schedule) {
        this.title = schedule.getTitle();
        this.username = schedule.getUsername();
        this.contents = schedule.getContents();
        this.password = schedule.getPassword();
        this.createdAt = LocalDateTime.now();
    }
}
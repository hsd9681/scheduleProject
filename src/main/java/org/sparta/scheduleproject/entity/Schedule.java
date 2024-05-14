package org.sparta.scheduleproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sparta.scheduleproject.dto.ScheduleRequestDto;


@Getter
@Setter
@NoArgsConstructor
public class Schedule extends Timestamped {
    private Long id;
    private String title;
    private String contents;
    private String username;
    private String password;




    public Schedule(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();

    }
}
package org.sparta.scheduleproject.dto;

import lombok.Getter;
import org.sparta.scheduleproject.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String username;
    private String contents;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> comments;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.username = schedule.getUsername();
        this.contents = schedule.getContents();
        this.password = schedule.getPassword();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
        this.comments = schedule.getComments().stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }
}

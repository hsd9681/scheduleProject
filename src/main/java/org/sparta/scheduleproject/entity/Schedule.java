package org.sparta.scheduleproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sparta.scheduleproject.dto.ScheduleRequestDto;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "schedule") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor


public class Schedule extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false, length = 500)
    private String title;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "contents", nullable = false, length = 500)
    private String contents;
    @Column(name = "password", nullable = false, length = 500)
    private String password;
    @Column(name = "comentList",nullable = false, length = 500)
    private String comentList;



    public Schedule(ScheduleRequestDto requestDto) {

        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();

    }
    public void update(ScheduleRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
    }
}

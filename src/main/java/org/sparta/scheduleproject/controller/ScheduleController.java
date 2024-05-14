package org.sparta.scheduleproject.controller;

import org.sparta.scheduleproject.dto.ScheduleRequestDto;
import org.sparta.scheduleproject.dto.ScheduleResponseDto;
import org.sparta.scheduleproject.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //private final Map<Long, Schedule> scheduleList = new HashMap<>();

    @PostMapping("/schedules")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto){
        Schedule schedule = new Schedule(requestDto);


        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체
        String sql = "INSERT INTO schedule (title, username, contents, password) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, schedule.getTitle());
                    preparedStatement.setString(2, schedule.getUsername());
                    preparedStatement.setString(3, schedule.getContents());
                    preparedStatement.setString(4, schedule.getPassword());

                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);

        // Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }
    //스케줄 하나 가져오기
    @GetMapping("/schedule/{id}")
    public List<ScheduleResponseDto> getoneSchedule(@PathVariable Long id) {
        String sql = "SELECT * FROM schedule where id =" +id+"";

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {

                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String username = rs.getString("username");
                String contents = rs.getString("contents");
                return new ScheduleResponseDto(id,title,username, contents);
            }
        });
    }
    //전체 조회
    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getAllSchedules(){
        String sql = "SELECT * FROM schedule order by createdAt desc";

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {

                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String username = rs.getString("username");
                String contents = rs.getString("contents");
                return new ScheduleResponseDto(id,title,username, contents);
            }
        });
    }
}

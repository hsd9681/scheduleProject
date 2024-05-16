package org.sparta.scheduleproject.service;

import org.sparta.scheduleproject.dto.ScheduleRequestDto;
import org.sparta.scheduleproject.dto.ScheduleResponseDto;
import org.sparta.scheduleproject.entity.Schedule;
import org.sparta.scheduleproject.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;

public class ScheduleService {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);

        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        Schedule saveSchedule =  scheduleRepository.save(schedule);

        // Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

    public List<ScheduleResponseDto> getoneSchedule(Long id) {
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

    public List<ScheduleResponseDto> getAllSchedules() {

        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        return scheduleRepository.findAll();


    }

    public ScheduleResponseDto updateMemo(Long id, ScheduleRequestDto requestDto) {

        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);


        Schedule schedule = scheduleRepository.findById(id);

        if(schedule != null&& schedule.getPassword().equals(requestDto.getPassword())) {
            // memo 내용 수정
            scheduleRepository.update(id, requestDto);
            ScheduleResponseDto reponse = new ScheduleResponseDto(schedule);
            return reponse;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    public Long deleteMemo(Long id, ScheduleRequestDto requestDto) {

        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        Schedule schedule = scheduleRepository.findById(id);
        if(schedule != null&& schedule.getPassword().equals(requestDto.getPassword())) {
            // memo 삭제

            scheduleRepository.delete(id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

}

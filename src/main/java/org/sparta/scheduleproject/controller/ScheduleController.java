package org.sparta.scheduleproject.controller;

import org.sparta.scheduleproject.dto.ScheduleRequestDto;
import org.sparta.scheduleproject.dto.ScheduleResponseDto;
import org.sparta.scheduleproject.service.ScheduleService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @PostMapping("/schedules")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto){

        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.createSchedule(requestDto);

    }
    //스케줄 하나 가져오기
    @GetMapping("/schedule/{id}")
    public List<ScheduleResponseDto> getoneSchedule(@PathVariable Long id) {
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.getoneSchedule(id);

    }
    //전체 조회
    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getAllSchedules(){
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.getAllSchedules();

    }
    //수정
    @PutMapping("/schedules/{id}")
    public ScheduleResponseDto updateMemo(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return  scheduleService.updateMemo(id, requestDto);
    }
        @DeleteMapping("/schedules/{id}")
    public Long deleteMemo(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return  scheduleService.deleteMemo(id, requestDto);
    }
}

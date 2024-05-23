package org.sparta.scheduleproject.controller;

import jakarta.validation.Valid;
import org.sparta.scheduleproject.dto.ScheduleRequestDto;
import org.sparta.scheduleproject.dto.ScheduleResponseDto;
import org.sparta.scheduleproject.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
    @PostMapping("/schedules")
    public ScheduleResponseDto createSchedule(@RequestBody @Valid ScheduleRequestDto requestDto){
        return scheduleService.createSchedule(requestDto);
    }
    private final List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png");

    //스케줄 하나 가져오기
    @GetMapping("/schedule/{id}")
    public List<ScheduleResponseDto> getoneSchedule(@PathVariable Long id) {
        return scheduleService.getoneSchedule(id);

    }
    //전체 조회
    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getAllSchedules(){
        return scheduleService.getAllSchedules();
    }
    //수정
    @PutMapping("/schedules/{id}")
    public ScheduleResponseDto updateMemo(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        return  scheduleService.updateMemo(id, requestDto);
    }
        @DeleteMapping("/schedules/{id}")
    public Long deleteMemo(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return  scheduleService.deleteMemo(id, requestDto);
    }
}

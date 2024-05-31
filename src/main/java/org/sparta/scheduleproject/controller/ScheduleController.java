package org.sparta.scheduleproject.controller;

import jakarta.validation.Valid;
import org.sparta.scheduleproject.dto.CommentRequestDto;
import org.sparta.scheduleproject.dto.ScheduleRequestDto;
import org.sparta.scheduleproject.dto.ScheduleResponseDto;
import org.sparta.scheduleproject.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
    public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/schedules")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto){
        return scheduleService.createSchedule(requestDto);
    }

    @GetMapping("/schedule/{id}")
    public List<ScheduleResponseDto> getoneSchedule(@PathVariable Long id) {
        return scheduleService.getoneSchedule(id);
    }

    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getAllSchedules(){
        return scheduleService.getAllSchedules();
    }

    @PutMapping("/schedules/{id}")
    public ScheduleResponseDto updateMemo(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.updateMemo(id, requestDto);
    }

    @DeleteMapping("/schedules/{id}")
    public Long deleteMemo(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.deleteMemo(id, requestDto);
    }

    @PostMapping("/schedules/{id}/comments")
    public ScheduleResponseDto addComment(@PathVariable Long id, @RequestBody @Valid CommentRequestDto requestDto) {
        return scheduleService.addComment(id, requestDto);
    }
    @PutMapping("/schedules/{id}/comments/{id2}")
    public ScheduleResponseDto updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @PathVariable Long id2) {
        return scheduleService.updateComment(id, requestDto, id2);
    }

    @DeleteMapping("/schedules/{id}/comments/{id2}")
    public Long deleteComment(@PathVariable Long id,@PathVariable Long id2, @RequestBody CommentRequestDto requestDto) {
        return scheduleService.deleteComment(id,id2, requestDto);
    }
}

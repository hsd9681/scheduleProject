package org.sparta.scheduleproject.controller;


import org.sparta.scheduleproject.dto.ScheduleRequestDto;
import org.sparta.scheduleproject.dto.ScheduleResponseDto;
import org.sparta.scheduleproject.entity.Schedule;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    @PostMapping("/schedules")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto){
        Schedule schedule = new Schedule(requestDto);


        Long maxId = scheduleList.size() > 0 ? Collections.max(scheduleList.keySet())+1 : 1;
        schedule.setId(maxId);
        scheduleList.put(schedule.getId(), schedule);

        ScheduleResponseDto scheduleresponseDto = new ScheduleResponseDto(schedule);

        return scheduleresponseDto;

    }
    //전체 조회
    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getAllSchedules(){
        List<ScheduleResponseDto> scheduleResponseDtos = scheduleList.values().stream()
                .map(ScheduleResponseDto::new).toList();

        return scheduleResponseDtos;
    }

    //스케줄 하나 가져오기
    @GetMapping("/schedule/{id}")
    public ScheduleResponseDto getoneSchedule(@PathVariable Long id) {
        if (scheduleList.containsKey(id)) {
            // 해당 스케줄 가져오기
            Schedule schedule = scheduleList.get(id);
            schedule.selectschedule(schedule);

            return new ScheduleResponseDto(schedule);
        }else {
            throw new IllegalArgumentException("선택한 스케줄 존재하지 않습니다.");
        }

    }
}

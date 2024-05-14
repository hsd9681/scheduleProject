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

    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getSchedules(){
        List<ScheduleResponseDto> scheduleResponseDtos = scheduleList.values().stream()
                .map(ScheduleResponseDto::new).toList();

        return scheduleResponseDtos;
    }


}

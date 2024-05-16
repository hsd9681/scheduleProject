package org.sparta.scheduleproject.service;

import org.sparta.scheduleproject.dto.ScheduleRequestDto;
import org.sparta.scheduleproject.dto.ScheduleResponseDto;
import org.sparta.scheduleproject.entity.Schedule;
import org.sparta.scheduleproject.repository.ScheduleRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component

public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);
        Schedule saveSchedule =  scheduleRepository.save(schedule);
        // Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
        return scheduleResponseDto;
    }

    public List<ScheduleResponseDto> getoneSchedule(Long id) {
        return scheduleRepository.findById(id).stream().map(ScheduleResponseDto::new).toList();
    }

    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleRepository.findAllByOrderByModifiedAtDesc().stream().map(ScheduleResponseDto::new).toList();
    }

    @Transactional
    public ScheduleResponseDto updateMemo(Long id, ScheduleRequestDto requestDto) {

        Schedule schedule = findSchedule(id);
        schedule.update(requestDto);
        return new ScheduleResponseDto(schedule);
    }

    public Long deleteMemo(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = findSchedule(id);
            scheduleRepository.delete(schedule);
            return id;
    }

    private  Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->{
            return new IllegalArgumentException("Schedule not found");
        });
    }

}

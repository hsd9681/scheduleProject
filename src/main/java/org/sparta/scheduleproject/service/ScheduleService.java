package org.sparta.scheduleproject.service;

import org.sparta.scheduleproject.dto.ScheduleRequestDto;
import org.sparta.scheduleproject.dto.ScheduleResponseDto;
import org.sparta.scheduleproject.entity.Schedule;
import org.sparta.scheduleproject.exception.InvalidPasswordException;
import org.sparta.scheduleproject.repository.ScheduleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);
        //System.out.println(schedule);
        Schedule saveSchedule =  scheduleRepository.save(schedule);
        // Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
        return scheduleResponseDto;
    }

    public List<ScheduleResponseDto> getoneSchedule(Long id) {
        return scheduleRepository.findById(id).stream().map(ScheduleResponseDto::new).toList();
    }

    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleRepository.findAllByOrderByCreatedAtDesc().stream().map(ScheduleResponseDto::new).toList();
    }

    @Transactional
    public ScheduleResponseDto updateMemo(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = findSchedule(id);

        if (!schedule.getPassword().equals(requestDto.getPassword())) {
            throw new InvalidPasswordException("password wrong");
        }

        schedule.update(requestDto);
        return new ScheduleResponseDto(schedule);
    }

    public Long deleteMemo(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = findSchedule(id);

        if (!schedule.getPassword().equals(requestDto.getPassword())) {
            throw new InvalidPasswordException("password wrong");
        }

        scheduleRepository.delete(schedule);
        return id;
    }

    private  Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->{
            return new IllegalArgumentException("Schedule not found");
        });
    }

}

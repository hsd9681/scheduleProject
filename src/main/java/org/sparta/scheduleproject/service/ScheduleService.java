package org.sparta.scheduleproject.service;

import org.sparta.scheduleproject.dto.ScheduleRequestDto;
import org.sparta.scheduleproject.dto.ScheduleResponseDto;
import org.sparta.scheduleproject.entity.Schedule;
import org.sparta.scheduleproject.repository.ScheduleRepository;
import org.springframework.stereotype.Component;

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
        return scheduleRepository.findOne(id);
    }

    public List<ScheduleResponseDto> getAllSchedules() {


        return scheduleRepository.findAll();


    }

    public ScheduleResponseDto updateMemo(Long id, ScheduleRequestDto requestDto) {

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

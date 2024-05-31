package org.sparta.scheduleproject.service;

import org.sparta.scheduleproject.dto.CommentRequestDto;
import org.sparta.scheduleproject.dto.CommentResponseDto;
import org.sparta.scheduleproject.dto.ScheduleRequestDto;
import org.sparta.scheduleproject.dto.ScheduleResponseDto;
import org.sparta.scheduleproject.entity.Comment;
import org.sparta.scheduleproject.entity.Schedule;
import org.sparta.scheduleproject.exception.InvalidPasswordException;
import org.sparta.scheduleproject.repository.CommentRepository;
import org.sparta.scheduleproject.repository.ScheduleRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;


    public ScheduleService(ScheduleRepository scheduleRepository, CommentRepository commentRepository) {
        this.scheduleRepository = scheduleRepository;
        this.commentRepository = commentRepository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(savedSchedule);

    }

    public List<ScheduleResponseDto> getoneSchedule(Long id) {
        return scheduleRepository.findById(id).stream()
                .map(ScheduleResponseDto::new).toList();
    }

    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(ScheduleResponseDto::new).toList();
    }

    @Transactional
    public ScheduleResponseDto updateMemo(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = findSchedule(id);

        if (!schedule.getPassword().equals(requestDto.getPassword())) {
            throw new InvalidPasswordException("Password wrong");
        }
        schedule.update(requestDto);
        return new ScheduleResponseDto(schedule);
    }

    public Long deleteMemo(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = findSchedule(id);

        if (!schedule.getPassword().equals(requestDto.getPassword())) {
            throw new InvalidPasswordException("Password wrong");
        }

        scheduleRepository.delete(schedule);
        return id;
    }

    public ScheduleResponseDto addComment(Long scheduleId, CommentRequestDto requestDto) {
        Schedule schedule = findSchedule(scheduleId);
        Comment comment = new Comment(requestDto, schedule); //null 이 들어가는데
        schedule.getComments().add(comment);
        commentRepository.save(comment); // 여기서 null이 해당값들을 찾아 jpa때문인가?
        return new ScheduleResponseDto(schedule);
    }
    @Transactional
    public ScheduleResponseDto updateComment(Long id, CommentRequestDto requestDto,Long commentId) {
        //어떻게 해당 게시물의 해당 댓을을 수정하고 삭제해야할까?
        Schedule schedule = findSchedule(id);
        Comment comment = findComment(id);
        if (!comment.getUsername().equals(requestDto.getUsername())) {
            throw new InvalidPasswordException("User wrong");
        }
        schedule.updateComment(commentId, requestDto);
        return new ScheduleResponseDto(schedule);
    }

    public Long deleteComment(Long id,Long commentId, CommentRequestDto requestDto) {
        Schedule schedule = findSchedule(id);
        schedule.deleteComment(commentId);
        Comment comment = findComment(commentId);
        if (!comment.getUsername().equals(requestDto.getUsername())) {
            throw new InvalidPasswordException("User wrong");
        }
        commentRepository.delete(comment);
        //스케줄레포로 접근해서 댓글이 삭제되게???
        return commentId;
    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
    }

    private Comment findComment(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
    }
}

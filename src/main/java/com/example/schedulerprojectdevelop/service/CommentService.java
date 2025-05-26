package com.example.schedulerprojectdevelop.service;

import com.example.schedulerprojectdevelop.dto.CommentResponseDto;
import com.example.schedulerprojectdevelop.entity.Comment;
import com.example.schedulerprojectdevelop.entity.Schedule;
import com.example.schedulerprojectdevelop.entity.User;
import com.example.schedulerprojectdevelop.repository.CommentRepository;
import com.example.schedulerprojectdevelop.repository.ScheduleRepository;
import com.example.schedulerprojectdevelop.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public CommentResponseDto save(Long scheduleId, String contents, String username){

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);
        User findUser = userRepository.findUserByUsernameOrElseThrow(username);
        Comment comment = new Comment(contents, findSchedule);
        comment.setUser(findUser);
        commentRepository.save(comment);

        return new CommentResponseDto(comment.getContents(), comment.getId(), comment.getSchedule().getId());
    }

    public List<CommentResponseDto> findAllByScheduleId(Long scheduleId){

        return commentRepository.findAllByScheduleId(scheduleId)
                .stream()
                .map(CommentResponseDto::toDto)
                .toList();
    }

    public CommentResponseDto findById(Long id){
        Comment findComment = commentRepository.findByIdOrElseThrow(id);

        return new CommentResponseDto(findComment.getContents(), findComment.getId(), findComment.getSchedule().getId());
    }

    public CommentResponseDto update(Long id, String contents){
        Comment findComment = commentRepository.findByIdOrElseThrow(id);

        findComment.setContents(contents);

        commentRepository.save(findComment);

        return new CommentResponseDto(findComment.getContents(), findComment.getId(), findComment.getSchedule().getId());
    }

    public void delete(Long id) { commentRepository.delete(commentRepository.findByIdOrElseThrow(id));}
}

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    /**
     * 일정 아이디를 받아
     * 일정에 댓글을 작성하는 메소드
     * @param scheduleId
     * @param contents
     * @param username
     * @return
     */
    public CommentResponseDto save(Long scheduleId, String contents, String username){

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);
        User findUser = userRepository.findUserByUsernameOrElseThrow(username);
        Comment comment = new Comment(contents, findSchedule);
        comment.setUser(findUser);
        commentRepository.save(comment);

        return new CommentResponseDto(comment.getContents(), comment.getId(), comment.getSchedule().getId());
    }

    /**
     * 일정 id로 일정에 달린 댓글을 모두 조회하는 메소드
     * @param scheduleId
     * @return
     */
    public List<CommentResponseDto> findAllByScheduleId(Long scheduleId){

        return commentRepository.findAllByScheduleId(scheduleId)
                .stream()
                .map(CommentResponseDto::toDto)
                .toList();
    }

    /**
     * 댓글 id로 댓글을 조회하는 메소드
     * @param id
     * @return
     */
    public CommentResponseDto findById(Long id){
        Comment findComment = commentRepository.findByIdOrElseThrow(id);

        return new CommentResponseDto(findComment.getContents(), findComment.getId(), findComment.getSchedule().getId());
    }

    /**
     * 모든 댓글을 조회하는 메소드
     * @param pageable
     * @return
     */
    public Page<CommentResponseDto> findAll(Pageable pageable){
        return commentRepository.findAll(pageable)
                .map(CommentResponseDto::toDto);
    }
    /**
     * 댓글 id를 받아 댓글을 수정하는 메소드
     * @param id
     * @param contents
     * @return
     */
    public CommentResponseDto update(Long id, String contents){
        Comment findComment = commentRepository.findByIdOrElseThrow(id);

        findComment.setContents(contents);

        commentRepository.save(findComment);

        return new CommentResponseDto(findComment.getContents(), findComment.getId(), findComment.getSchedule().getId());
    }

    /**
     * 댓글 id를 받아 댓글을 삭제하는 메소드
     * @param id
     */
    public void delete(Long id) { commentRepository.delete(commentRepository.findByIdOrElseThrow(id));}
}

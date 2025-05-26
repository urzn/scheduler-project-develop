package com.example.schedulerprojectdevelop.controller;

import com.example.schedulerprojectdevelop.dto.CommentResponseDto;
import com.example.schedulerprojectdevelop.dto.CreateCommentRequestDto;
import com.example.schedulerprojectdevelop.dto.MessageResponseDto;
import com.example.schedulerprojectdevelop.dto.UpdateCommentRequestDto;
import com.example.schedulerprojectdevelop.entity.Comment;
import com.example.schedulerprojectdevelop.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentResponseDto> save(
            @Valid
            @PathVariable Long scheduleId,
            @RequestBody CreateCommentRequestDto requestDto){

        CommentResponseDto commentResponseDto =
                commentService.save(scheduleId, requestDto.getContents(), requestDto.getUsername());

        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<List<CommentResponseDto>> findAllByScheduleId(@PathVariable Long scheduleId){

        List<CommentResponseDto> commentResponseDtoList =
                commentService.findAllByScheduleId(scheduleId);

        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("comments/{id}")
    public ResponseEntity<CommentResponseDto> findById(@PathVariable Long id){

        CommentResponseDto commentResponseDto =
                commentService.findById(id);

        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @GetMapping("/comments")
    public ResponseEntity<Page<CommentResponseDto>> findAll(
            @PageableDefault(size = 10, sort = "modifiedAt",
                    direction = Sort.Direction.DESC) Pageable pageable){

        Page<CommentResponseDto> responseDtoPage = commentService.findAll(pageable);

        return new ResponseEntity<>(responseDtoPage, HttpStatus.OK);
    }
    
    @PatchMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> updateById(
            @PathVariable Long id,
            @RequestBody UpdateCommentRequestDto requestDto
            ){

        CommentResponseDto commentResponseDto =
                commentService.update(id, requestDto.getContents());

        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<MessageResponseDto> deleteById(@PathVariable Long id){

        commentService.delete(id);

        MessageResponseDto messageResponseDto = new MessageResponseDto("댓글이 삭제되었습니다.");
        return new ResponseEntity<>(messageResponseDto, HttpStatus.OK);
    }

}

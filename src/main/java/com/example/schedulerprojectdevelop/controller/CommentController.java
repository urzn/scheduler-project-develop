package com.example.schedulerprojectdevelop.controller;

import com.example.schedulerprojectdevelop.dto.CommentResponseDto;
import com.example.schedulerprojectdevelop.dto.CreateCommentRequestDto;
import com.example.schedulerprojectdevelop.entity.Comment;
import com.example.schedulerprojectdevelop.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

}

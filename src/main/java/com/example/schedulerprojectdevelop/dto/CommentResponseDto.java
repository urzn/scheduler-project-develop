package com.example.schedulerprojectdevelop.dto;

import com.example.schedulerprojectdevelop.entity.Comment;
import com.example.schedulerprojectdevelop.repository.CommentRepository;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private final String contents;

    private final Long id;

    private final Long scheduleId;

    public CommentResponseDto(String contents, Long id, Long scheduleId){
        this.contents = contents;
        this.id = id;
        this.scheduleId = scheduleId;
    }

    public static CommentResponseDto toDto (Comment comment){
        return new CommentResponseDto(comment.getContents(), comment.getId(), comment.getSchedule().getId());
    }
}

package com.example.schedulerprojectdevelop.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class CreateCommentRequestDto {

    @Length(max=200, message="댓글은 200글자 이하여야 합니다.")
    private final String contents;

    @Length(min=1, max=4, message="유저명은 4글자 이하여야 합니다.")
    private String username;

    public CreateCommentRequestDto(String contents, String username){
        this.contents = contents;
        this.username = username;
    }
}

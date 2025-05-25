package com.example.schedulerprojectdevelop.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class UpdateScheduleRequestDto {

    @Length(max=10, message="일정 제목은 10글자 이하여야 합니다.")
    private String title;

    @Length(max=200, message="일정 내용은 200글자 이하여야 합니다.")
    private String contents;

    @Length(min=1, max=4, message="유저명은 4글자 이하여야 합니다.")
    private String username;

    public UpdateScheduleRequestDto(String title, String contents, String username){
        this.title = title;
        this.contents = contents;
        this.username = username;
    }
}

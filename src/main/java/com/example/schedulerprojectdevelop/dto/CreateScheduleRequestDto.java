package com.example.schedulerprojectdevelop.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    private String title;

    private String contents;

    private String username;

    public CreateScheduleRequestDto(String title, String contents, String username){
        this.title = title;
        this.contents = contents;
        this.username = username;
    }
}

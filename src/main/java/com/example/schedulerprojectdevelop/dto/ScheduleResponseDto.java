package com.example.schedulerprojectdevelop.dto;

import com.example.schedulerprojectdevelop.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private Long id;

    private String title;

    private String contents;

    public ScheduleResponseDto(Long id, String title, String contents){
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public static ScheduleResponseDto toDto(Schedule schedule){
        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents());
    }

}

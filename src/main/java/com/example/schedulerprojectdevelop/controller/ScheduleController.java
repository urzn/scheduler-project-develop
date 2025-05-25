package com.example.schedulerprojectdevelop.controller;

import com.example.schedulerprojectdevelop.dto.CreateScheduleRequestDto;
import com.example.schedulerprojectdevelop.dto.ScheduleResponseDto;
import com.example.schedulerprojectdevelop.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

}

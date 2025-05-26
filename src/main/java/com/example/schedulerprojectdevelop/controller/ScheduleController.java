package com.example.schedulerprojectdevelop.controller;

import com.example.schedulerprojectdevelop.dto.CreateScheduleRequestDto;
import com.example.schedulerprojectdevelop.dto.ScheduleResponseDto;
import com.example.schedulerprojectdevelop.dto.UpdateScheduleRequestDto;
import com.example.schedulerprojectdevelop.repository.ScheduleRepository;
import com.example.schedulerprojectdevelop.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@Valid @RequestBody CreateScheduleRequestDto requestDto){

        ScheduleResponseDto scheduleResponseDto =
                scheduleService.save(
                        requestDto.getTitle(),
                        requestDto.getContents(),
                        requestDto.getUsername()
                );

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll(){

        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();

        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id){

        ScheduleResponseDto scheduleResponseDto = scheduleService.findById(id);

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(@Valid @PathVariable Long id,
                                                      @RequestBody UpdateScheduleRequestDto requestDto){

        ScheduleResponseDto scheduleResponseDto =
                    scheduleService.update(id,
                            requestDto.getTitle(),
                            requestDto.getContents());

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){

        scheduleService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}

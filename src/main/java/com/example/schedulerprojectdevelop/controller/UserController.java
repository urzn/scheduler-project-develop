package com.example.schedulerprojectdevelop.controller;

import com.example.schedulerprojectdevelop.dto.UserResponseDto;
import com.example.schedulerprojectdevelop.dto.SignUpRequestDto;
import com.example.schedulerprojectdevelop.dto.SignUpResponseDto;
import com.example.schedulerprojectdevelop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto){

        SignUpResponseDto signUpResponseDto =
                userService.signUp(
                    requestDto.getUsername(),
                    requestDto.getPassword(),
                    requestDto.getEmail()
                );

        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }
}

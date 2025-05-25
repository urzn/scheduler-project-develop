package com.example.schedulerprojectdevelop.controller;

import com.example.schedulerprojectdevelop.dto.LoginRequestDto;
import com.example.schedulerprojectdevelop.dto.MessageResponseDto;
import com.example.schedulerprojectdevelop.dto.UserResponseDto;
import com.example.schedulerprojectdevelop.entity.User;
import com.example.schedulerprojectdevelop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<MessageResponseDto> login(@RequestBody LoginRequestDto requestDto, HttpServletRequest request){

        User user = userService.login(requestDto.getEmail(), requestDto.getPassword());

        HttpSession session = request.getSession();
        session.setAttribute("sessionKey", user);

        MessageResponseDto messageResponseDto = new MessageResponseDto("로그인되었습니다.");

        return new ResponseEntity<>(messageResponseDto, HttpStatus.OK);
    }

}

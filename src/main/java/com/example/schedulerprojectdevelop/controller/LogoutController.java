package com.example.schedulerprojectdevelop.controller;

import com.example.schedulerprojectdevelop.dto.MessageResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logout")
@RequiredArgsConstructor
public class LogoutController {

    @PostMapping
    public ResponseEntity<MessageResponseDto> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 세션 없으면 null반환
        if (session != null) {
            session.invalidate(); // 세션 만료
        }

        MessageResponseDto messageResponseDto = new MessageResponseDto("로그아웃되었습니다.");

        return new ResponseEntity<>(messageResponseDto, HttpStatus.OK);
    }
}

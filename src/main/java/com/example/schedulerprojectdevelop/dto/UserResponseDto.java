package com.example.schedulerprojectdevelop.dto;

import com.example.schedulerprojectdevelop.repository.UserRepository;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private final String username;

    private final String email;

    public UserResponseDto(String username, String email){
        this.username = username;
        this.email = email;
    }
}

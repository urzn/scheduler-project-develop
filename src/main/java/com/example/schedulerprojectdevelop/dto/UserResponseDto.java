package com.example.schedulerprojectdevelop.dto;

import com.example.schedulerprojectdevelop.entity.User;
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

    public static UserResponseDto toDto(User user){
        return new UserResponseDto(user.getUsername(), user.getEmail());
    }
}

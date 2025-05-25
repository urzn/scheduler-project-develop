package com.example.schedulerprojectdevelop.service;


import com.example.schedulerprojectdevelop.dto.SignUpResponseDto;
import com.example.schedulerprojectdevelop.entity.User;
import com.example.schedulerprojectdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SignUpResponseDto signUp(String username, String password, String email){

        User user = new User(username, password, email);

        User savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }


}

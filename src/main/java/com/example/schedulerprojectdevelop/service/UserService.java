package com.example.schedulerprojectdevelop.service;


import com.example.schedulerprojectdevelop.config.PasswordEncoder;
import com.example.schedulerprojectdevelop.dto.SignUpResponseDto;
import com.example.schedulerprojectdevelop.dto.UserResponseDto;
import com.example.schedulerprojectdevelop.entity.User;
import com.example.schedulerprojectdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpResponseDto signUp(String username, String password, String email){

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(username, encodedPassword, email);

        User savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    public List<UserResponseDto> findAll(){

        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::toDto)
                .toList();
    }

    public UserResponseDto findByName(String username){

        User findUser = userRepository.findUserByUsernameOrElseThrow(username);

        return new UserResponseDto(findUser.getUsername(), findUser.getEmail());
    }

    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword){

        User findUser = userRepository.findUserByIdOrElseThrow(id);

        if(!passwordEncoder.matches(oldPassword, findUser.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        String newEncodedPassword = passwordEncoder.encode(newPassword);

        findUser.updatePassword(newEncodedPassword);

    }

    public void deleteUser(Long id){
        userRepository.delete(userRepository.findUserByIdOrElseThrow(id));
    }

    public User login(String email, String password){
        User findUser = userRepository.findUserByEmailOrElseThrow(email);

        if(!passwordEncoder.matches(password, findUser.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        return findUser;

    }



}

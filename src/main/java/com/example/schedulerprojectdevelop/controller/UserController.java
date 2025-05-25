package com.example.schedulerprojectdevelop.controller;

import com.example.schedulerprojectdevelop.dto.UpdatePasswordRequestDto;
import com.example.schedulerprojectdevelop.dto.UserResponseDto;
import com.example.schedulerprojectdevelop.dto.SignUpRequestDto;
import com.example.schedulerprojectdevelop.dto.SignUpResponseDto;
import com.example.schedulerprojectdevelop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto requestDto){

        SignUpResponseDto signUpResponseDto =
                userService.signUp(
                    requestDto.getUsername(),
                    requestDto.getPassword(),
                    requestDto.getEmail()
                );

        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(){

        List<UserResponseDto> userResponseDtoList = userService.findAll();

        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDto> findUserByName(@PathVariable String username){

        UserResponseDto userResponseDto = userService.findByName(username);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(
            @Valid
            @PathVariable Long id,
            @RequestBody UpdatePasswordRequestDto requestDto
            ){

        userService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){

        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

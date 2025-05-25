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

    /**
     * 회원가입 메소드
     * 패스워드는 암호화된다
     * @param username
     * @param password
     * @param email
     * @return
     */
    public SignUpResponseDto signUp(String username, String password, String email){

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(username, encodedPassword, email);

        User savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    /**
     * 저장된 유저를 모두 조회하는 메소드
     * @return
     */
    public List<UserResponseDto> findAll(){

        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::toDto)
                .toList();
    }

    /**
     * 유저네임으로 유저를 조회하는 메소드
     * @param username
     * @return
     */
    public UserResponseDto findByName(String username){

        User findUser = userRepository.findUserByUsernameOrElseThrow(username);

        return new UserResponseDto(findUser.getUsername(), findUser.getEmail());
    }

    /**
     * 패스워드 변경 메소드.
     * 기존 패스워드가 맞는지 확인하고
     * 새로운 패스워드를 암호화해 저장한다
     * @param id
     * @param oldPassword
     * @param newPassword
     */
    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword){

        User findUser = userRepository.findUserByIdOrElseThrow(id);

        if(!passwordEncoder.matches(oldPassword, findUser.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        String newEncodedPassword = passwordEncoder.encode(newPassword);

        findUser.updatePassword(newEncodedPassword);

    }

    /**
     * id로 유저를 삭제하는 메소드
     * @param id
     */
    public void deleteUser(Long id){
        userRepository.delete(userRepository.findUserByIdOrElseThrow(id));
    }

    /**
     * 로그인 메소드
     * 이메일, 패스워드로 로그인하고
     * User 객체를 반환한다
     * @param email
     * @param password
     * @return
     */
    public User login(String email, String password){
        User findUser = userRepository.findUserByEmailOrElseThrow(email);

        if(!passwordEncoder.matches(password, findUser.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        return findUser;

    }



}

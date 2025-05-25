package com.example.schedulerprojectdevelop.repository;

import com.example.schedulerprojectdevelop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
    Optional<User> findUserById(Long id);
    Optional<User> findUserByEmail(String email);

    default User findUserByUsernameOrElseThrow(String username){
        return findUserByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저네임 " + username + "이 존재하지 않습니다."));
    }

    default User findUserByIdOrElseThrow(Long id){
        return findUserById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저 id가 존재하지 않습니다."));
    }

    default User findUserByEmailOrElseThrow(String email){
        return findUserByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "이메일이 존재하지 않습니다."));

    }
}

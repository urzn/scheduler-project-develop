package com.example.schedulerprojectdevelop.repository;

import com.example.schedulerprojectdevelop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    default User findUserByUsernameOrElseThrow(String username){
        return findUserByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저네임 " + username + "이 존재하지 않습니다."));
    }
}

package com.example.schedulerprojectdevelop.repository;

import com.example.schedulerprojectdevelop.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//JpaRepository를 상속하기 때문에 레포지토리 어노테이션을 붙이지 않아도된다.
//JpaRepository의 프록시 구현체는 스프링 빈 컨테이너에 자동등록됨.

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    /**
     * 일정 id로 일정 조회
     * @param id
     * @return
     */
    default Schedule findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다."));
    }
}

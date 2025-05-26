package com.example.schedulerprojectdevelop.service;

import com.example.schedulerprojectdevelop.dto.ScheduleResponseDto;
import com.example.schedulerprojectdevelop.entity.Schedule;
import com.example.schedulerprojectdevelop.entity.User;
import com.example.schedulerprojectdevelop.repository.ScheduleRepository;
import com.example.schedulerprojectdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    /**
     * 새로운 일정을 생성하는 메소드
     *
     * 유저네임으로 유저를 찾고,
     * Schedule 객체를 만들어 저장,
     * ScheduleResponseDto 반환
     *
     * @param title
     * @param contents
     * @param username
     * @return
     */
    public ScheduleResponseDto save(String title, String contents, String username){

        User findUser = userRepository.findUserByUsernameOrElseThrow(username);

        Schedule schedule = new Schedule(title, contents);
        schedule.setUser(findUser);

        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents());
    }

    /**
     * 저장된 일정을 모두 조회하는 메소드
     *
     * 레포지토리에서 Schedule 타입으로 받은 값들을
     * Dto로 변환 후 리스트 형태로 반환
     * @return
     */
    public Page<ScheduleResponseDto> findAll(Pageable pageable){
        return scheduleRepository.findAll(pageable)
                .map(ScheduleResponseDto::toDto);
    }

    public ScheduleResponseDto findById(Long id){
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents());
    }
    /**
     * 일정 id로 일정을 수정하는 메소드
     * @param id
     * @param title
     * @param contents
     * @return
     */
    public ScheduleResponseDto update(Long id, String title, String contents){

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        findSchedule.setTitle(title);
        findSchedule.setContents(contents);

        scheduleRepository.save(findSchedule);

        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents());
    }

    /**
     * 일정 id로 일정을 삭제하는 메소드
     * @param id
     */
    public void delete(Long id){
        scheduleRepository.delete(scheduleRepository.findByIdOrElseThrow(id));
    }
}

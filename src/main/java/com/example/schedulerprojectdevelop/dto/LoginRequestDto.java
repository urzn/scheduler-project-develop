package com.example.schedulerprojectdevelop.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class LoginRequestDto {

    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "유효한 이메일 주소를 입력해주세요."
    )
    private final String email;

    @Length(min=4, max=20, message="비밀번호는 4글자 이상 20글자 이하여야 합니다.")
    private final String password;

    public LoginRequestDto(String email, String password){
        this.email = email;
        this.password = password;
    }
}

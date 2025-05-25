package com.example.schedulerprojectdevelop.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class UpdatePasswordRequestDto {

    @Length(min=4, max=20, message="비밀번호는 4글자 이상 20글자 이하여야 합니다.")
    private final String oldPassword;

    @Length(min=4, max=20, message="비밀번호는 4글자 이상 20글자 이하여야 합니다.")
    private final String newPassword;

    public UpdatePasswordRequestDto(String oldPassword, String newPassword){
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}

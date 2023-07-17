package com.planning.user.dto;

import com.planning.user.entity.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserSignUpDto {
    private String email;
    private String username;
    private String password;
    private UserRoleEnum role = UserRoleEnum.USER;
}

package com.planning.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserLoginDto {
    private String username;
    private String password;
}

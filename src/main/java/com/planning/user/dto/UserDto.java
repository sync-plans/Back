package com.planning.user.dto;

import com.planning.user.entity.User;
import com.planning.user.entity.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
    private String email;
    private String username;
    private UserRoleEnum role;

    public UserDto(User user){
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.role = user.getRole();
    }
}

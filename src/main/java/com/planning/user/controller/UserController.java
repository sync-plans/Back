package com.planning.user.controller;

import com.planning.user.dto.UserSignUpDto;
import com.planning.user.entity.User;
import com.planning.user.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    @NonNull
    private UserService userService;

    @PostMapping
    public User signUpUser(@RequestBody UserSignUpDto requestDto){
        return this.userService.signUpUser(requestDto);
    }
}

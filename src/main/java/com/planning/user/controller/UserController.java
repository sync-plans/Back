package com.planning.user.controller;

import com.planning.jwt.JwtUtil;
import com.planning.user.dto.UserSignUpDto;
import com.planning.user.entity.User;
import com.planning.user.entity.UserRoleEnum;
import com.planning.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    @NonNull
    private UserService userService;
    @NonNull
    private JwtUtil jwtUtil;

    @PostMapping
    public User signUpUser(@RequestBody UserSignUpDto requestDto) {
        return this.userService.signUpUser(requestDto);
    }

    @GetMapping("/create-jwt")
    public String createJwt(HttpServletResponse res) {
        String token = jwtUtil.createToken("choi", UserRoleEnum.USER);
        jwtUtil.addJwtToCookie(token, res);
        return "createJwt :" + token;
    }

    @PostMapping("/test")
    public UserSignUpDto testFunction(@RequestBody UserSignUpDto requestDto) {
        System.out.println(requestDto);
        return requestDto;
    }

    @GetMapping("/hi")
    public String testHi(){
        return "hi";
    }
}

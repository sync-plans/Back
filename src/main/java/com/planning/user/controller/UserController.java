package com.planning.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.planning.jwt.JwtUtil;
import com.planning.user.dto.UserSignUpDto;
import com.planning.user.entity.User;
import com.planning.user.service.UserService;
import jakarta.servlet.http.Cookie;
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

    @PostMapping
    public User signUpUser(@RequestBody UserSignUpDto requestDto){
        return this.userService.signUpUser(requestDto);
    }

    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        String token = userService.kakaoLogin(code);
        Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, token.substring(7));
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/";
    }

}

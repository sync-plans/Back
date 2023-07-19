package com.planning.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.planning.jwt.JwtUtil;
import com.planning.user.dto.UserSignUpDto;
import com.planning.user.entity.User;
import com.planning.user.entity.UserRoleEnum;
import com.planning.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j(topic = "UserController")
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

    @GetMapping("/kakao/callback")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        String token = userService.kakaoLogin(code);
        jwtUtil.addJwtToCookie(token, response);
        log.info(response.getHeader(JwtUtil.AUTHORIZATION_HEADER));
        return "success";
    }
}

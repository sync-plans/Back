package com.planning.user.service;

import com.planning.user.dto.UserSignUpDto;
import com.planning.user.entity.User;
import com.planning.user.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @NonNull
    private UserRepository userRepsitory;

    @NonNull
    private PasswordEncoder passwordEncoder;

    public User signUpUser(UserSignUpDto requestDto) {
        Optional<User> byUsername = userRepsitory.findByUsername(requestDto.getUsername());
        if (byUsername.isPresent())
            throw new IllegalArgumentException("중복된 이름이 존재합니다.");
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        return this.userRepsitory.save(new User(requestDto));
    }
}

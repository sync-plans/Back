package com.planning.user.entity;

import com.planning.user.dto.UserSignUpDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    private Long kakaoId;

    public User(UserSignUpDto requestDto) {
        this.email = requestDto.getEmail();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.role = requestDto.getRole();
    }

    public boolean equals(User user) {
        return user.getUsername().equals(this.username) && user.getPassword().equals(this.password);
    }

    public User(String email, String username, String password, Long kakaoId, UserRoleEnum role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.kakaoId = kakaoId;
        this.role = role;
    }

    public User kakaoIdUpdate(Long kakaoId) {
        this.kakaoId = kakaoId;
        return this;
    }

}

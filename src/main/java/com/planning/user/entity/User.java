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

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(UserSignUpDto requestDto) {
        this.email = requestDto.getEmail();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.role = requestDto.getRole();
    }

    public boolean equals(User user) {
        return user.getUsername().equals(this.username) && user.getPassword().equals(this.password);
    }
}

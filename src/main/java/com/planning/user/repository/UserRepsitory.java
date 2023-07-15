package com.planning.user.repository;

import com.planning.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepsitory extends JpaRepository<User, Long> {
}

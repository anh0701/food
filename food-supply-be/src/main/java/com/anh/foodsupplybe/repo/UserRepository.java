package com.anh.foodsupplybe.repo;

import com.anh.foodsupplybe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

package com.anh.foodsupplybe.service;

import com.anh.foodsupplybe.dto.LoginDto;
import com.anh.foodsupplybe.dto.SignUpDto;
import com.anh.foodsupplybe.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User getUser();

    Map<String, Object> login(LoginDto loginDto);

    List<User> getAllUsers();

    Map<String, Object> signUp(SignUpDto signUpDto);

    User findUserByUsername(String username);

    void updateUserStats(Long userId, Double orderAmount);
}


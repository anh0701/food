package com.anh.foodsupplybe.service;

import com.anh.foodsupplybe.dto.LoginDto;
import com.anh.foodsupplybe.model.User;

import java.util.List;

public interface UserService {
    User getUser();
    String login(LoginDto loginDto);
    List<User> getAllUsers();
}


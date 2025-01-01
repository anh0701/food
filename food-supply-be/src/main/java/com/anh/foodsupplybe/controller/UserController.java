package com.anh.foodsupplybe.controller;

import com.anh.foodsupplybe.model.User;
import com.anh.foodsupplybe.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<User> userProfile() {
        return ResponseEntity.ok(userService.getUser());
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}

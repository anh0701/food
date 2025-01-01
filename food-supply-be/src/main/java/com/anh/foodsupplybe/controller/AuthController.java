package com.anh.foodsupplybe.controller;

import com.anh.foodsupplybe.dto.LoginDto;
import com.anh.foodsupplybe.dto.SignUpDto;
import com.anh.foodsupplybe.model.User;
import com.anh.foodsupplybe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(userService.login(loginDto));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Map<String, Object>> signUp(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(userService.signUp(signUpDto));
    }

}

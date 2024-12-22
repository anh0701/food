package com.anh.foodsupplybe.service;

import com.anh.foodsupplybe.config.JwtTokenService;
import com.anh.foodsupplybe.dto.LoginDto;
import com.anh.foodsupplybe.model.Permission;
import com.anh.foodsupplybe.model.User;
import com.anh.foodsupplybe.repo.PermissionRepository;
import com.anh.foodsupplybe.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Map<String, Object> login(LoginDto loginDto) {
        final Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User user = userRepository.findByUsername(loginDto.getUsername());
        Map<String, Object> result = new HashMap<>();
        result.put("token", jwtTokenService.generateToken(user.getUsername(), user.getRoles()));
        result.put("username", user.getUsername());
        result.put("roles", user.getRoles());
        return result;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername());
    }
}
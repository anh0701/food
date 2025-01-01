package com.anh.foodsupplybe.service.impl;

import com.anh.foodsupplybe.config.JwtTokenService;
import com.anh.foodsupplybe.dto.LoginDto;
import com.anh.foodsupplybe.dto.SignUpDto;
import com.anh.foodsupplybe.model.Permission;
import com.anh.foodsupplybe.model.Role;
import com.anh.foodsupplybe.model.User;
import com.anh.foodsupplybe.repo.PermissionRepository;
import com.anh.foodsupplybe.repo.UserRepository;
import com.anh.foodsupplybe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.anh.foodsupplybe.model.RoleType.USER;

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
    public Map<String, Object> signUp(SignUpDto signUpDto) {
        Map<String, Object> result = new HashMap<>();
        try {

            User user = findUserByUsername(signUpDto.getUsername());
            if (user != null) {
                throw new Exception("Username is already in use");
            }
            Role userRole = new Role();
            userRole.setRole(USER);

            Permission readPermission = new Permission();
            readPermission.setName("read");

            Set<Permission> userPermissions = new HashSet<>();
            userPermissions.add(readPermission);

            User userNew = new User();
            userNew.setName(signUpDto.getName());
            userNew.setUsername(signUpDto.getUsername());
            userNew.setEmail(signUpDto.getEmail());
            userNew.setRoles(Collections.singleton(userRole));
            userNew.setPassword(bcryptEncoder.encode(signUpDto.getPassword()));
            userRepository.save(userNew);

            result.put("token", jwtTokenService.generateToken(userNew.getUsername(), userNew.getRoles()));
            result.put("username", userNew.getUsername());
            result.put("roles", userNew.getRoles());
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername());
    }
}
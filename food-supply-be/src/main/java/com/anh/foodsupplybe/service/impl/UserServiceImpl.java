package com.anh.foodsupplybe.service.impl;

import com.anh.foodsupplybe.config.JwtTokenService;
import com.anh.foodsupplybe.constants.RoleConstants;
import com.anh.foodsupplybe.dto.LoginDto;
import com.anh.foodsupplybe.dto.SignUpDto;
import com.anh.foodsupplybe.model.MemberLevel;
import com.anh.foodsupplybe.model.Permission;
import com.anh.foodsupplybe.model.Role;
import com.anh.foodsupplybe.model.User;
import com.anh.foodsupplybe.repo.MemberLevelRepository;
import com.anh.foodsupplybe.repo.PermissionRepository;
import com.anh.foodsupplybe.repo.RoleRepository;
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
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MemberLevelRepository memberLevelRepository;

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
        result.put("username", user.getUsername());
        Set<Permission> permissions = new HashSet<>();

        for (Role role : user.getRoles()) {
            permissions.addAll(role.getPermissions());
        }
        result.put("permissions", permissions);
//        result.put("roles", user.getRoles());
        result.put("token", jwtTokenService.generateToken(user.getUsername(), permissions));
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

            User userCheck = findUserByUsername(signUpDto.getUsername());
            if (userCheck != null) {
                throw new Exception("Username is already in use");
            }
            Permission permission = new Permission();
            permission.setName("READ_PERMISSION");
            Set<Permission> permissions = new HashSet<>();
            permissions.add(permission);

            Role userRole = roleRepository.findByRole(RoleConstants.ROLE_USER);
            if (userRole == null) {
                userRole = new Role();
                userRole.setRole(RoleConstants.ROLE_USER);
                Set<Permission> userPermissions = new HashSet<>();
                userPermissions.add(permission);
                userRole.setPermissions(userPermissions);
                userRole = roleRepository.save(userRole);
            }

            User user = new User();
            user.setUsername(signUpDto.getUsername());
            user.setPassword(bcryptEncoder.encode(signUpDto.getPassword()));
            user.setName(signUpDto.getUsername());
            user.setEmail(signUpDto.getEmail());
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            user.setRoles(roles);
            userRepository.save(user);
//            Map<String, Object> result = new HashMap<>();
            result.put("username", user.getUsername());
            result.put("token", jwtTokenService.generateToken(user.getUsername(), permissions));
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
    public void updateUserStats(Long userId, Double orderAmount) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setTotalSpent(user.getTotalSpent() + orderAmount);
        user.setTotalPurchases(user.getTotalPurchases() + 1);
        upgradeMemberLevel(user);
        userRepository.save(user);
    }

    private void upgradeMemberLevel(User user) {
        MemberLevel currentLevel = user.getMemberLevel();

        List<MemberLevel> memberLevels = memberLevelRepository.findAll();
        memberLevels.sort(Comparator.comparingDouble(MemberLevel::getMinSpent));

        for (int i = 0; i < memberLevels.size(); i++) {
            MemberLevel nextLevel = memberLevels.get(i);

            if (user.getTotalSpent() >= nextLevel.getMinSpent() &&
                    user.getTotalPurchases() >= nextLevel.getMinPurchases()) {

                if (currentLevel == null || nextLevel.getMinSpent() > currentLevel.getMinSpent()) {
                    user.setMemberLevel(nextLevel);
                    break;
                }
            }
        }
        userRepository.save(user);
    }

    @Override
    public User getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername());
    }
}
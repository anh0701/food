package com.anh.foodsupplybe.config;

import com.anh.foodsupplybe.constants.RoleConstants;
import com.anh.foodsupplybe.model.Permission;
import com.anh.foodsupplybe.model.Product;
import com.anh.foodsupplybe.model.Role;
import com.anh.foodsupplybe.model.User;
import com.anh.foodsupplybe.repo.PermissionRepository;
import com.anh.foodsupplybe.repo.ProductRepository;
import com.anh.foodsupplybe.repo.RoleRepository;
import com.anh.foodsupplybe.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class LoadDatabase {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private ProductRepository productRepository;

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            if (productRepository.findAll().isEmpty()) {
                productRepository.save(new Product(null, "Product 1", 2000));
                productRepository.save(new Product(null, "Product 2", 30000));
                productRepository.save(new Product(null, "Product 3", 4000));
            }

            if (permissionRepository.findAll().isEmpty()) {
                permissionRepository.save(new Permission(null, "GET_PRODUCT"));
                permissionRepository.save(new Permission(null, "ADD_PRODUCT"));
                permissionRepository.save(new Permission(null, "DELETE_PRODUCT"));
                permissionRepository.save(new Permission(null, "UPDATE_PRODUCT"));
                permissionRepository.save(new Permission(null, "GET_DISCOUNT"));
                permissionRepository.save(new Permission(null, "ADD_DISCOUNT"));
                permissionRepository.save(new Permission(null, "GET_HISTORY_DISCOUNT"));
                permissionRepository.save(new Permission(null, "ADD_INVOICE"));
                permissionRepository.save(new Permission(null, "GET_INVOICE"));
                permissionRepository.save(new Permission(null, "GET_REPORT"));
            }

            Permission readPermission = permissionRepository.findByName("GET_PRODUCT");

            Role adminRole = roleRepository.findByRole(RoleConstants.ROLE_ADMIN);
            if (adminRole == null) {
                adminRole = new Role();
                adminRole.setRole(RoleConstants.ROLE_ADMIN);
                List<Permission> permissionsList =  permissionRepository.findAll();
                Set<Permission> adminPermissions = new HashSet<>(permissionsList);
                adminRole.setPermissions(adminPermissions);
                adminRole = roleRepository.save(adminRole);
            }

            Role userRole = roleRepository.findByRole(RoleConstants.ROLE_USER);
            if (userRole == null) {
                userRole = new Role();
                userRole.setRole(RoleConstants.ROLE_USER);
                Set<Permission> userPermissions = new HashSet<>();
                userPermissions.add(readPermission);
                userRole.setPermissions(userPermissions);
                userRole = roleRepository.save(userRole);
            }

            User admin = new User();
            admin.setName("Admin Name");
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            roles.add(userRole);
            admin.setRoles(roles);
            admin.setPassword(passwordEncoder.encode("Admin123@"));
            userRepository.save(admin);

            User user = new User();
            user.setName("User Name");
            user.setUsername("user");
            user.setEmail("user@example.com");
            user.setRoles(Collections.singleton(userRole));
            user.setPassword(passwordEncoder.encode("userpass"));
            userRepository.save(user);

            System.out.println("Sample data initialized");
        };
    }
}

package com.anh.foodsupplybe.config;

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
import java.util.Set;

import static com.anh.foodsupplybe.model.RoleType.ADMIN;
import static com.anh.foodsupplybe.model.RoleType.USER;

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

            productRepository.save(new Product(null, "Product 1", 2000));
            productRepository.save(new Product(null, "Product 2", 30000));
            productRepository.save(new Product(null, "Product 3", 4000));
            Permission readPermission = permissionRepository.findByName("READ_PERMISSION");
            if (readPermission == null) {
                readPermission = new Permission();
                readPermission.setName("READ_PERMISSION");
                readPermission = permissionRepository.save(readPermission);
            }
            Permission writePermission = permissionRepository.findByName("PERMISSION_ADD_PRODUCT");
            if (writePermission == null) {
                writePermission = new Permission();
                writePermission.setName("PERMISSION_ADD_PRODUCT");
                writePermission = permissionRepository.save(writePermission);
            }

            Permission updatePermission = permissionRepository.findByName("PERMISSION_UPDATE_PRODUCT");
            if (updatePermission == null) {
                updatePermission = new Permission();
                updatePermission.setName("PERMISSION_UPDATE_PRODUCT");
                updatePermission = permissionRepository.save(updatePermission);
            }

            Permission deleteProduct = permissionRepository.findByName("PERMISSION_DELETE_PRODUCT");
            if (deleteProduct == null) {
                deleteProduct = new Permission();
                deleteProduct.setName("PERMISSION_DELETE_PRODUCT");
                deleteProduct = permissionRepository.save(deleteProduct);
            }

            Role adminRole = roleRepository.findByRole(ADMIN);
            if (adminRole == null) {
                adminRole = new Role();
                adminRole.setRole(ADMIN);
                Set<Permission> adminPermissions = new HashSet<>();
                adminPermissions.add(readPermission);
                adminPermissions.add(writePermission);
                adminPermissions.add(updatePermission);
                adminPermissions.add(deleteProduct);
                adminRole.setPermissions(adminPermissions);
                adminRole = roleRepository.save(adminRole);
            }

            Role userRole = roleRepository.findByRole(USER);
            if (userRole == null) {
                userRole = new Role();
                userRole.setRole(USER);
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

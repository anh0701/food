package com.anh.foodsupplybe.repo;

import com.anh.foodsupplybe.model.Role;
import com.anh.foodsupplybe.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}

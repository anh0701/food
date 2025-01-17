package com.anh.foodsupplybe.repo;

import com.anh.foodsupplybe.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByName(String name);
}

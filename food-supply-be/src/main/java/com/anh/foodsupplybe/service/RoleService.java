package com.anh.foodsupplybe.service;

import com.anh.foodsupplybe.dto.RoleRequest;
import com.anh.foodsupplybe.model.Role;

import java.util.List;

public interface RoleService {
    Role addRole(RoleRequest role);
    List<Role> getRoles();
}

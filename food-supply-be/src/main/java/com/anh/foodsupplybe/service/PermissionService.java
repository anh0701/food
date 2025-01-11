package com.anh.foodsupplybe.service;

import com.anh.foodsupplybe.model.Permission;

import java.util.List;

public interface PermissionService {
    Permission addPermission(Permission permission);
    List<Permission> getAllPermissions();
}

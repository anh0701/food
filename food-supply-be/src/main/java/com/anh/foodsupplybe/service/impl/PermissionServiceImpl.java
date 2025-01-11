package com.anh.foodsupplybe.service.impl;

import com.anh.foodsupplybe.model.Permission;
import com.anh.foodsupplybe.repo.PermissionRepository;
import com.anh.foodsupplybe.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Permission addPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }
}
package com.anh.foodsupplybe.service.impl;

import com.anh.foodsupplybe.dto.RoleRequest;
import com.anh.foodsupplybe.model.Permission;
import com.anh.foodsupplybe.model.Role;
import com.anh.foodsupplybe.repo.PermissionRepository;
import com.anh.foodsupplybe.repo.RoleRepository;
import com.anh.foodsupplybe.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Role addRole(RoleRequest role) {
        Role roleSave = new Role();
        roleSave.setRole(role.getRole());
        roleSave.setDescription(role.getDescription());
        Set<Permission> permissions = new HashSet<>();
        for(RoleRequest.PermissionRequest permissionRequest : role.getPermissions()) {
            if (permissionRequest.getId() > 0){
                Permission permission = permissionRepository.findById(permissionRequest.getId()).get();
                permissions.add(permission);
            }
        }
        roleSave.setPermissions(permissions);
        return roleRepository.save(roleSave);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}

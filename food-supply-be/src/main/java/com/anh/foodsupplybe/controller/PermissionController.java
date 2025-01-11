package com.anh.foodsupplybe.controller;

import com.anh.foodsupplybe.model.Permission;
import com.anh.foodsupplybe.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @PostMapping("/add")
    public ResponseEntity<Permission> addPermission(@RequestBody Permission permission) {
        Permission permiss =  permissionService.addPermission(permission);
        return ResponseEntity.ok(permiss);
    }

    @GetMapping
    public ResponseEntity<List<Permission>> getPermissions() {
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }
}

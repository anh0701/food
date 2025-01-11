package com.anh.foodsupplybe.controller;

import com.anh.foodsupplybe.dto.RoleRequest;
import com.anh.foodsupplybe.model.Role;
import com.anh.foodsupplybe.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<Role> addRole(@RequestBody RoleRequest role) {
        return ResponseEntity.ok(roleService.addRole(role));
    }

    @GetMapping
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok(roleService.getRoles());
    }
}
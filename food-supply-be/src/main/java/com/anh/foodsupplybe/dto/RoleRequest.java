package com.anh.foodsupplybe.dto;

import java.util.Set;

public class RoleRequest {
    private String role;
    private String description;
    private Set<PermissionRequest> permissions;

    public static class PermissionRequest {
        private Long id;

        public PermissionRequest(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<PermissionRequest> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionRequest> permissions) {
        this.permissions = permissions;
    }
}


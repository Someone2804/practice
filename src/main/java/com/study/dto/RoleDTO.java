package com.study.dto;

import com.study.store.entity.enumR.ERole;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDTO {

    ERole role;

    Set<UserDTO> users;

    public RoleDTO(ERole role, Set<UserDTO> users) {
        this.role = role;
        this.users = users;
    }

    public Set<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDTO> users) {
        this.users = users;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "role='" + role + '\'' +
                '}';
    }
}

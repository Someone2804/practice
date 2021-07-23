package com.study.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {

    String username;

    final Set<RoleDTO> roles;
    @JsonIgnore
    String password;

    final List<MessageDTO> messages;

    public UserDTO(String username, Set<RoleDTO> roles, String password, List<MessageDTO> messages) {
        this.username = username;
        this.roles = roles;
        this.password = password;
        this.messages = messages;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", roles=" + roles +
                '}';
    }
}

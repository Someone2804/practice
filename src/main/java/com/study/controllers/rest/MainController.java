package com.study.controllers.rest;

import com.study.service.MessageService;
import com.study.service.RoleService;
import com.study.service.UserService;
import com.study.store.entity.User;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainController {

    final UserService userService;
    final MessageService messageService;
    final RoleService roleService;

    @Autowired
    public MainController(UserService userService, MessageService messageService, RoleService roleService) {
        this.userService = userService;
        this.messageService = messageService;
        this.roleService = roleService;
    }

    @PostMapping("/login")
    public void login(@RequestParam String username,
                      @RequestParam String password){
        User user = new User(username, password);
        try {
            userService.authenticateUser(user);
        }catch (AuthenticationException e){
            throw new AuthenticationCredentialsNotFoundException("Incorrect login or password.");
        }
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String adminPage(){
        return "Admin Page";
    }
}

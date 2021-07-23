package com.study.controllers.rest;


import com.study.dto.mappers.UserMapper;
import com.study.service.UserService;
import com.study.store.entity.User;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/registration")
public class RegistrationController {

    final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registrationPage(){
        return "reg page";
    }

    @PostMapping
    public ResponseEntity<?> registration(@RequestParam String username,
                                          @RequestParam String password){
        User user = new User(username, password);
        user = userService.saveUser(user);
        userService.authenticateUser(user);
        return new ResponseEntity<>(UserMapper.mapper.userToUserDTO(user), HttpStatus.CREATED);
    }
}

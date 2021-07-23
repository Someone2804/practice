package com.study.controllers.rest;


import com.study.dto.MessageDTO;
import com.study.dto.mappers.MessageMapper;
import com.study.dto.mappers.UserMapper;
import com.study.service.MessageService;
import com.study.service.RoleService;
import com.study.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/messages")
public class MessagesController {

    final UserService userService;
    final MessageService messageService;
    final RoleService roleService;

    @Autowired
    public MessagesController(UserService userService, MessageService messageService, RoleService roleService) {
        this.userService = userService;
        this.messageService = messageService;
        this.roleService = roleService;
    }

    @GetMapping
    public List<MessageDTO> getAll(){
        return MessageMapper.mapper.messagesToMessagesDto(messageService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMessageCreator(@PathVariable Long id){
        return new ResponseEntity<>(UserMapper.mapper.userToUserDTO(userService.findUserById(id)), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMessage(@RequestParam String text){
        return new ResponseEntity<>(MessageMapper.mapper.messageToMessageDto(messageService.createMessage(text)), HttpStatus.CREATED);
    }
}

package com.study.service;

import com.study.dto.MessageDTO;
import com.study.dto.mappers.MessageMapper;
import com.study.exceptions.EmptyMessageException;
import com.study.exceptions.NotFoundException;
import com.study.store.entity.Message;
import com.study.store.entity.User;
import com.study.store.repository.MessageRepo;
import com.study.store.repository.UserRepo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class MessageService {

    final MessageRepo messageRepo;
    final UserRepo userRepo;

    @Autowired
    public MessageService(MessageRepo messageRepo, UserRepo userRepo) {
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
    }

    public List<Message> findAll(){
        return messageRepo.findAll();
    }

    public List<Message> findAllLike(String text){
        return messageRepo.findAllByTextContaining(text);
    }

    public Message createMessage(String text){
        if(text == null || text.isBlank()){
            throw new EmptyMessageException("Message can't be empty");
        }
        Message message = new Message(text, (User) SecurityContextHolder
                                            .getContext()
                                            .getAuthentication()
                                            .getPrincipal());
        return messageRepo.saveAndFlush(message);
    }

    public Message findById(Long id) {
        return messageRepo.findById(id).orElseThrow(() -> new NotFoundException("Message not found."));
    }

}

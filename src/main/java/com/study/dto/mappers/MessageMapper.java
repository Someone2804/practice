package com.study.dto.mappers;

import com.study.dto.MessageDTO;
import com.study.dto.RoleDTO;
import com.study.dto.UserDTO;
import com.study.store.entity.Message;
import com.study.store.entity.Role;
import com.study.store.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MessageMapper {

    MessageMapper mapper = Mappers.getMapper(MessageMapper.class);

    MessageDTO messageToMessageDto(Message message);

    List<MessageDTO> messagesToMessagesDto(List<Message> messages);

    @Mapping(target = "messages", ignore = true)
    UserDTO userToUserDTO(User user);

    @Mapping(target = "users", ignore = true)
    RoleDTO roleToRoleDTO(Role role);

}

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
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper{

    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    List<UserDTO> userToUserDTOList(List<User> users);

    @Mapping(target = "users", ignore = true)
    RoleDTO roleToRoleDTO(Role role);

    @Mapping(target = "user", ignore = true)
    MessageDTO messageToMessageDTO(Message message);
}

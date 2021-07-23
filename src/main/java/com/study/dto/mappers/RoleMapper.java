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

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper mapper = Mappers.getMapper(RoleMapper.class);

    RoleDTO roleToRoleDTO(Role role);

    Set<RoleDTO> roleToRoleDTO(Set<Role> roles);

    @Mapping(target = "roles", ignore = true)
    UserDTO userToUserDTO(User user);

    @Mapping(target = "user", ignore = true)
    MessageDTO messageToMessageDTO(Message message);
}

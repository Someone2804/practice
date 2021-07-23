package com.study.service;

import com.study.dto.UserDTO;
import com.study.dto.mappers.UserMapper;
import com.study.exceptions.AlreadyExistException;
import com.study.exceptions.NotFoundException;
import com.study.exceptions.ServiceException;
import com.study.store.entity.Role;
import com.study.store.entity.User;
import com.study.store.entity.enumR.ERole;
import com.study.store.repository.MessageRepo;
import com.study.store.repository.RoleRepo;
import com.study.store.repository.UserRepo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService implements UserDetailsService {

    final RoleRepo roleRepo;

    final UserRepo userRepo;

    final MessageRepo messageRepo;

    final AuthenticationManager authenticationManager;

    final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(RoleRepo roleRepo, UserRepo userRepo, MessageRepo messageRepo, AuthenticationManager authenticationManager, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.messageRepo = messageRepo;
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(s);
        if(user == null){
            throw new UsernameNotFoundException("User not found.");
        }
        return user;
    }

    public User findUserById(Long id) throws UsernameNotFoundException{
        return userRepo.findById(id).orElseThrow(() -> new NotFoundException("Id " + id + " not found."));
    }

    public List<UserDTO> findAll(){
        return UserMapper.mapper.userToUserDTOList(userRepo.findAll());
    }

    public User saveUser(User user){
        User userfromdb = userRepo.findByUsername(user.getUsername());

        if(userfromdb != null){
            throw new AlreadyExistException("Saving user already exist.");
        }

        userRepo.saveAndFlush(fillUser(user));
        return user;
    }

    private User fillUser(User user){
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepo.findByRole(ERole.ROLE_USER));
        user.setRoles(roles);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        user.setMessages(new ArrayList<>());
        return user;
    }

    public void authenticateUser(User user){
        try {
            UserDetails userDetails = loadUserByUsername(user.getUsername());

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());

        if(auth.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(auth);
        }else{
            throw new AuthenticationCredentialsNotFoundException("Incorrect login or password.");
        }

        }catch (UsernameNotFoundException e){
            throw new AuthenticationCredentialsNotFoundException("Incorrect login or password.");
        }
    }

    public void deleteUser(Long id) throws ServiceException {
            userRepo.deleteById(id);

    }

}

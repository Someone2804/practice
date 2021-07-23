package com.study.service;

import com.study.store.entity.Role;
import com.study.store.entity.enumR.ERole;
import com.study.store.repository.RoleRepo;
import com.study.store.repository.UserRepo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleService {

    final RoleRepo roleRepo;
    final UserRepo userRepo;

    @Autowired
    public RoleService(RoleRepo roleRepo, UserRepo userRepo) {
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
    }

    public Role findByName(ERole role) {
        return roleRepo.findByRole(role);
    }
}

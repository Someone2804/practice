package com.study.store.repository;

import com.study.store.entity.Role;
import com.study.store.entity.enumR.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByRole(ERole role);
}

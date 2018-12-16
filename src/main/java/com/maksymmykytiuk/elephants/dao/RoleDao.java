package com.maksymmykytiuk.elephants.dao;

import com.maksymmykytiuk.elephants.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
    Role findByRoleIgnoreCase(String name);
}
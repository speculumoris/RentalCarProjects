package com.saferent.repository;

import com.saferent.domain.Role;
import com.saferent.domain.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {


    Optional<Role> findByType(RoleType type);

}

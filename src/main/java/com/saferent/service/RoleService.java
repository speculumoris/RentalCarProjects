package com.saferent.service;

import com.saferent.domain.Role;
import com.saferent.domain.enums.RoleType;
import com.saferent.dto.response.ResponseMessage;
import com.saferent.exception.ResourceNotFoundException;
import com.saferent.exception.message.ErrorMessage;
import com.saferent.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByType(RoleType type) {
        Role role = roleRepository.findByType(type).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessage.ROLE_NOT_FOUND_EXCEPTION, type.name())));
        return role;
    }
}

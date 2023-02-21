package com.saferent.service;

import com.saferent.domain.User;
import com.saferent.exception.ResourceNotFoundException;
import com.saferent.exception.message.ErrorMessage;
import com.saferent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_EXCEPTION, email)));
        return user;
    }
}

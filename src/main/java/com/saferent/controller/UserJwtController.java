package com.saferent.controller;
import com.saferent.dto.request.RegisterRequest;
import com.saferent.dto.response.*;
import com.saferent.security.jwt.*;
import com.saferent.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserJwtController {
    // !!! Bu class'da sadece Login ve Register işlemleri yapılacak
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    // !!! Register
    // !!! Register
    @PostMapping("/register")
    public ResponseEntity<SfResponse> registerUser(@Valid
                                                   @RequestBody RegisterRequest registerRequest) {
        userService.saveUser(registerRequest);

        SfResponse response = new SfResponse();
        response.setMessage(ResponseMessage.REGISTER_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
}



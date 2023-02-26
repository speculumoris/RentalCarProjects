package com.saferent.dto.request;

import lombok.*;

import javax.validation.constraints.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserUpdateRequest {
    @Size(max=50)
    @NotBlank(message="Please provide your First name")
    private String firstName;

    @Size(max=50)
    @NotBlank(message="Please provide your Last name")
    private String lastName;

    @Size(min=5  , max=80)
    @Email(message="Please provide your email")
    private String email;

    @Size(min=4, max=20, message="Please provide Correct Size of Password")
    @NotBlank(message="Please provide your password")
    private String password;

    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
            message = "Please provide valid phone number")
    @Size(max=14)
    @NotBlank(message="Please provide your phone number")
    private String phoneNumber;

    @Size(max=100)
    @NotBlank(message="Please provide your address")
    private String address;

    @Size(max=15)
    @NotBlank(message="Please provide your zip-code")
    private String zipCode;

    private Boolean builtIn ;

    private Set<String> roles ;
}

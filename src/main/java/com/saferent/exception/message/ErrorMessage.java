package com.saferent.exception.message;

public class ErrorMessage {

    public final static String RESOURCE_NOT_FOUND_EXCEPTION = "Resource with id %s not found";
    public final static String ROLE_NOT_FOUND_EXCEPTION = "Role : %s not found";
    public final static String USER_NOT_FOUND_EXCEPTION = "User with email: %s not found";
    public final static String JWTTOKEN_ERROR_MESSAGE = "JWT Token Validation Error: %s";
    public final static String EMAIL_ALREADY_EXIST_MESSAGE = "Email: %s already exist";
    public final static String PRINCIPAL_FOUND_MESSAGE = "User not found";
    public final static String NOT_PERMITTED_METHOD_MESSAGE = "You don't have any permission to change this data";
    public final static String PASSWORD_NOT_MATCHED_MESSAGE = "Your passwords are not matched";
}

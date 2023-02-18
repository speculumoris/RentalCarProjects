package com.saferent.exception;

import com.saferent.exception.message.*;
import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;
import org.springframework.web.servlet.mvc.method.annotation.*;

import java.util.*;
import java.util.stream.*;

@ControllerAdvice // merkezi exception handle etmek için
public class SafeRentExceptionHandler extends ResponseEntityExceptionHandler {

        // AMACIM : custom bir exception sistemini kurmak, gelebilecek exceptionları
        // override ederek, istediğim yapıda cevap verilmesini sağlamak

    private ResponseEntity<Object> buildResponseEntity(ApiResponseError error) {
        return new ResponseEntity<>(error,error.getStatus());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {

        ApiResponseError error = new ApiResponseError(HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getDescription(false)
        );

        return buildResponseEntity(error);

    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> errors = ex.getBindingResult().getFieldErrors().
                stream().
                map(e->e.getDefaultMessage()).
                collect(Collectors.toList());

        ApiResponseError error = new ApiResponseError(HttpStatus.BAD_REQUEST,
                errors.get(0).toString(),
                request.getDescription(false))  ;
        return buildResponseEntity(error);
    }




    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {

        ApiResponseError error = new ApiResponseError(HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                request.getDescription(false));
        return buildResponseEntity(error);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleGeneralException( Exception ex, WebRequest request) {

        ApiResponseError error = new ApiResponseError(HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                request.getDescription(false));

        return buildResponseEntity(error);
    }

}

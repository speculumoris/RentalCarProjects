package com.saferent.exception;

import com.saferent.exception.message.*;
import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;
import org.springframework.web.servlet.mvc.method.annotation.*;

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


}

package com.jmbullion.demoapp.exceptionhandler;

import com.jmbullion.demoapp.exceptions.ServiceException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Map<String, String>> handleServiceException(ServiceException e) {
        Map<String, String> serviceException = new HashMap<>();
        serviceException.put("errorCode", e.getErrorCode());
        serviceException.put("errorDesc", e.getErrorDesc());
        return new ResponseEntity<>(serviceException, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException ex,
             HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> errorMap = new HashMap<>();
        List<String> errorList = ex.getBindingResult().
                getFieldErrors().stream().
                map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        errorMap.put("errors", errorList);
        errorMap.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(errorMap, status);
    }
}

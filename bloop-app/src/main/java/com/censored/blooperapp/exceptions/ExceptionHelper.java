package com.censored.blooperapp.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionHelper extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public final ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.error("ResourceNotFoundException {}", ex.getMessage());
        return new ResponseEntity<>(getMessageBody(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public final ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("IllegalArgumentException {}", ex.getMessage());
        return new ResponseEntity<>(getMessageBody(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private Map<String, Object> getMessageBody(String message) {
        return getMessageBody("message", message);
    }

    private Map<String, Object> getMessageBody(String key, String message) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(key, message);
        return map;
    }

}

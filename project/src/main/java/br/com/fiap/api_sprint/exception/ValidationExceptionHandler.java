package br.com.fiap.api_sprint.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {

    Map<String, String> exceptions = new HashMap<>();
    ex.getBindingResult().getFieldErrors()
        .forEach(fieldError -> exceptions.put(fieldError.getField(), fieldError.getDefaultMessage()));

    return new ResponseEntity<>(exceptions, HttpStatus.BAD_REQUEST);
  }
}

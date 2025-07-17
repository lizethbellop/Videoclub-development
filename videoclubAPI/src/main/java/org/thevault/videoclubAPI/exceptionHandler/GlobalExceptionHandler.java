package org.thevault.videoclubAPI.exceptionHandler;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.thevault.videoclubAPI.exceptionHandler.custom.IncorrectCredentialsException;
import org.thevault.videoclubAPI.exceptionHandler.validation.ValidationErrorResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleDatabaseError(DataAccessException ex){
        ErrorResponse error = new ErrorResponse(
                "DATABASE ERROR",
                "There was an error while connecting to the database",
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleIncorrectCredentials(IncorrectCredentialsException ex){
        ErrorResponse error = new ErrorResponse("Incorrect credentials",
                ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex){
        ValidationErrorResponse errorResponse = new ValidationErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError("Validation failed");
        errorResponse.setTimestamp(LocalDateTime.now());

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        });

        errorResponse.setFieldErrors(fieldErrors);
        errorResponse.setSummary(String.format("Validation failed for %d fields", fieldErrors.size()));

        return ResponseEntity.badRequest().body(errorResponse);
    }
}

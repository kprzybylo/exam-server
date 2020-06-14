package pl.edu.prz.ai.exam.users.application.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.edu.prz.ai.exam.users.application.response.ApiError;
import pl.edu.prz.ai.exam.users.domain.exception.UsersException;

@ControllerAdvice
public class UsersExceptionHandler {
    @ExceptionHandler({UsersException.class})
    public ResponseEntity<ApiError> handleDomainException(UsersException exception) {
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(
                        ApiError.builder()
                                .timestamp(System.currentTimeMillis())
                                .status(exception.getStatusCode().value())
                                .error(exception.getStatusCode().name())
                                .message(exception.getMessage())
                                .build()
                );
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> "\"" + fieldError.getField() + "\": \"" + fieldError.getCode() + "\"")
                .reduce((s, s2) -> s + ", " + s2)
                .map(result -> "{" + result + "}")
                .orElse(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiError.builder()
                                .timestamp(System.currentTimeMillis())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .error(HttpStatus.BAD_REQUEST.name())
                                .message(message)
                                .build()
                );
    }
}

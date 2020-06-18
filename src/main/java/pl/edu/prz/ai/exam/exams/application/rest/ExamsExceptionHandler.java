package pl.edu.prz.ai.exam.exams.application.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.edu.prz.ai.exam.exams.application.response.ApiError;
import pl.edu.prz.ai.exam.exams.domain.exception.ExamsException;

@ControllerAdvice
public class ExamsExceptionHandler {
    @ExceptionHandler({ ExamsException.class })
    public ResponseEntity<ApiError> handleExamsException(ExamsException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(
                        ApiError.builder()
                                .timestamp(System.currentTimeMillis())
                                .status(e.getStatus().value())
                                .error(e.getStatus().name())
                                .message(e.getMessage())
                                .build()
                );
    }
}

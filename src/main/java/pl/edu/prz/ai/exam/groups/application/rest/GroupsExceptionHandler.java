package pl.edu.prz.ai.exam.groups.application.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.edu.prz.ai.exam.groups.application.response.ApiError;
import pl.edu.prz.ai.exam.groups.domain.exception.GroupsException;

@ControllerAdvice
public class GroupsExceptionHandler {
    @ExceptionHandler({ GroupsException.class })
    public ResponseEntity<ApiError> handleGroupsException(GroupsException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(
                    ApiError.builder()
                            .error(e.getStatus().name())
                            .status(e.getStatus().value())
                            .message(e.getMessage())
                            .timestamp(System.currentTimeMillis())
                            .build()
                );
    }
}

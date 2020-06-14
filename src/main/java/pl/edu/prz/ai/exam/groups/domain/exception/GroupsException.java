package pl.edu.prz.ai.exam.groups.domain.exception;

import org.springframework.http.HttpStatus;

public class GroupsException extends RuntimeException {
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}

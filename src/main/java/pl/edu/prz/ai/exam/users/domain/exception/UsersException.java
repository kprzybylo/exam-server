package pl.edu.prz.ai.exam.users.domain.exception;

import org.springframework.http.HttpStatus;

public class UsersException extends RuntimeException {
    public HttpStatus getStatusCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public String getMessage() {
        return "An error occurred while processing request.";
    }
}

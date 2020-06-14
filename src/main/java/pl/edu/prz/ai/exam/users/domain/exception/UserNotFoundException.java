package pl.edu.prz.ai.exam.users.domain.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends UsersException {
    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
        return "User was not found.";
    }
}

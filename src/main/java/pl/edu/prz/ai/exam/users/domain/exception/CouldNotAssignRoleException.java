package pl.edu.prz.ai.exam.users.domain.exception;

import org.springframework.http.HttpStatus;

public class CouldNotAssignRoleException extends UsersException {
    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public String getMessage() {
        return "Could not find appropriate role to assign to user.";
    }
}

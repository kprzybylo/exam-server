package pl.edu.prz.ai.exam.groups.domain.exception;

import org.springframework.http.HttpStatus;

public class GroupNameAlreadyInUseException extends GroupsException {
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getMessage() {
        return "Group with given name already exists.";
    }
}

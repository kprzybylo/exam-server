package pl.edu.prz.ai.exam.groups.domain.exception;

import org.springframework.http.HttpStatus;

public class GroupNotFoundException extends GroupsException {
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
        return "Searched group does not exists.";
    }
}

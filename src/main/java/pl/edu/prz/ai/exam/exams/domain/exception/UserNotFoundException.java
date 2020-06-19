package pl.edu.prz.ai.exam.exams.domain.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ExamsException {
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
        return "Provided user was not found.";
    }
}

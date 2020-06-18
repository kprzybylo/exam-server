package pl.edu.prz.ai.exam.exams.domain.exception;

import org.springframework.http.HttpStatus;

public class CouldNotAcquireLoggedUserException extends ExamsException {
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public String getMessage() {
        return "Could not acquire logged user.";
    }
}

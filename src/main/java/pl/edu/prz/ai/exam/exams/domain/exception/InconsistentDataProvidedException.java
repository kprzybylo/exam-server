package pl.edu.prz.ai.exam.exams.domain.exception;

import org.springframework.http.HttpStatus;

public class InconsistentDataProvidedException extends ExamsException {
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getMessage() {
        return "Provided data is inconsistent.";
    }
}

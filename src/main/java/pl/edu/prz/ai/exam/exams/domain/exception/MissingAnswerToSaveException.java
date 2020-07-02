package pl.edu.prz.ai.exam.exams.domain.exception;

import org.springframework.http.HttpStatus;

public class MissingAnswerToSaveException extends ExamsException {
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getMessage() {
        return "Answer to question was not provided.";
    }
}

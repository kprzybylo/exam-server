package pl.edu.prz.ai.exam.exams.application.request;

import lombok.Value;
import pl.edu.prz.ai.exam.exams.application.validation.ExistingQuestion;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
public class CreateAnswer {
    @NotNull @ExistingQuestion
    Long questionId;

    @NotNull @NotEmpty
    String answerText;

    Boolean isCorrectAnswer;
}

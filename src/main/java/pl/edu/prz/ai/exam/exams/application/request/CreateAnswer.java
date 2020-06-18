package pl.edu.prz.ai.exam.exams.application.request;

import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
public class CreateAnswer {
    @NotNull @NotEmpty
    String answerText;

    Boolean isCorrectAnswer;
}

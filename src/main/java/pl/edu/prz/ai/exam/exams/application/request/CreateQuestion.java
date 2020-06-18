package pl.edu.prz.ai.exam.exams.application.request;

import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Value
public class CreateQuestion {
    @NotNull @NotEmpty
    String question;

    Boolean isOpenQuestion;

    List<CreateAnswer> possibleAnswers;
}

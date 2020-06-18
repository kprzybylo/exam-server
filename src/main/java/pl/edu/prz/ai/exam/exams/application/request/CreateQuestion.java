package pl.edu.prz.ai.exam.exams.application.request;

import lombok.Value;
import pl.edu.prz.ai.exam.exams.application.validation.ExistingExam;
import pl.edu.prz.ai.exam.exams.domain.Answer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Value
public class CreateQuestion {
    @NotNull @ExistingExam
    Long examId;

    @NotNull @NotEmpty
    String question;

    Boolean isOpenQuestion;

    List<Answer> possibleAnswers;
}

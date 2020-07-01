package pl.edu.prz.ai.exam.exams.application.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SubmitAnswer {
    Long answerId;
    String answerText;
}

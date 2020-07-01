package pl.edu.prz.ai.exam.exams.application.response;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ExamQuestion {
    Long questionId;
    String question;
    String attachmentUrl;
    List<String> possibleAnswers;
}

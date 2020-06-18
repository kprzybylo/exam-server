package pl.edu.prz.ai.exam.exams.application.response;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
public class CreatedExam {
    Long id;
    String examName;
    Long availableTimeInMinutes;
    Date availableFrom;
    Date availableTo;
}

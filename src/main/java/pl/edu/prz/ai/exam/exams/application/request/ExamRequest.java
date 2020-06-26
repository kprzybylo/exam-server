package pl.edu.prz.ai.exam.exams.application.request;

import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Value
public class ExamRequest {
    @NotNull @NotEmpty
    String examName;

    @NotNull @Min(1)
    Long availableTimeInMinutes;

    Date availableFrom;
    Date availableTo;
}

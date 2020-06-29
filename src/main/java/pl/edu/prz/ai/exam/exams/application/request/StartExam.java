package pl.edu.prz.ai.exam.exams.application.request;

import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;

import javax.validation.constraints.NotNull;

@Value
@NoArgsConstructor
public class StartExam {
    @NotNull @NonFinal
    Integer availabilityInHours;
}

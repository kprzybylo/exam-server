package pl.edu.prz.ai.exam.exams.application.validation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.prz.ai.exam.exams.domain.repository.ExamRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExistingExamValidator implements ConstraintValidator<ExistingExam, Long> {
    ExamRepository examRepository;

    @Override
    public boolean isValid(Long examId, ConstraintValidatorContext context) {
        return examRepository.findById(examId).isPresent();
    }
}

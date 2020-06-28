package pl.edu.prz.ai.exam.exams.application.validation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.prz.ai.exam.exams.domain.exception.AnswerNotFoundException;
import pl.edu.prz.ai.exam.exams.domain.repository.AnswerRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExistingAnswerValidator implements ConstraintValidator<ExistingAnswer, Long> {
    AnswerRepository answerRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(answerRepository.findById(value) == null) {
            throw new AnswerNotFoundException();
        }

        return true;
    }
}

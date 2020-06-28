package pl.edu.prz.ai.exam.exams.application.validation;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.prz.ai.exam.exams.domain.exception.QuestionNotFoundException;
import pl.edu.prz.ai.exam.exams.domain.repository.QuestionRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExistingQuestionValidator implements ConstraintValidator<ExistingQuestion, Long> {
    QuestionRepository questionRepository;

    @Override
    public boolean isValid(Long questionId, ConstraintValidatorContext context) {
        if (questionRepository.findById(questionId) != null) {
            return true;
        }

        throw new QuestionNotFoundException();
    }
}

package pl.edu.prz.ai.exam.groups.application.validation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.prz.ai.exam.groups.domain.repository.ExamGroupRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UniqueGroupNameValidator implements ConstraintValidator<UniqueGroupName, String> {
    ExamGroupRepository examGroupRepository;

    @Override
    public boolean isValid(String groupName, ConstraintValidatorContext context) {
        return examGroupRepository.findByGroupName(groupName).isEmpty();
    }
}

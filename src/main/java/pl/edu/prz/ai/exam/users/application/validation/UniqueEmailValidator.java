package pl.edu.prz.ai.exam.users.application.validation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.prz.ai.exam.users.domain.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return userRepository.findByEmail(email).isEmpty();
    }
}

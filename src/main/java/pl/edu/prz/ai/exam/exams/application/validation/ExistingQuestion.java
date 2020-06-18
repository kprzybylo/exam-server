package pl.edu.prz.ai.exam.exams.application.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistingQuestionValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistingQuestion {
    String message() default "Given question does not exists.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

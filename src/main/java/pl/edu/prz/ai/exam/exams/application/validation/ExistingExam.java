package pl.edu.prz.ai.exam.exams.application.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistingExamValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistingExam {
    String message() default "Given exam does not exists.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

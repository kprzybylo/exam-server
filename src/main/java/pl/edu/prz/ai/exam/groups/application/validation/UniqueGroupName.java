package pl.edu.prz.ai.exam.groups.application.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueGroupNameValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueGroupName {
    String message() default "Group name already taken.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

package pl.edu.prz.ai.exam.groups.application.request;

import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;

import javax.validation.constraints.NotNull;

@Value
@NoArgsConstructor
public class UserAssignmentChange {
    @NotNull @NonFinal Long userId;
}

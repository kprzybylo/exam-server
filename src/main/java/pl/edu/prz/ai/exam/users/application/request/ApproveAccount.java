package pl.edu.prz.ai.exam.users.application.request;

import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
@NoArgsConstructor
public class ApproveAccount {
    @NotNull @NotEmpty
    @NonFinal String newRoleName;
}

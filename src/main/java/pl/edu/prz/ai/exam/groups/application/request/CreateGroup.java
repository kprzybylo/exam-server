package pl.edu.prz.ai.exam.groups.application.request;

import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;
import pl.edu.prz.ai.exam.groups.application.validation.UniqueGroupName;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
@NoArgsConstructor
public class CreateGroup {
    @NotNull @NotEmpty @UniqueGroupName
    @NonFinal String groupName;
}

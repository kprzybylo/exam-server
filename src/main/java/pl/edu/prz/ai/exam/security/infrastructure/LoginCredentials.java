package pl.edu.prz.ai.exam.security.infrastructure;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
class LoginCredentials {
    String username;
    String password;
}

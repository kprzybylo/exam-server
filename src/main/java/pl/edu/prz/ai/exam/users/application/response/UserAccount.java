package pl.edu.prz.ai.exam.users.application.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserAccount {
    Long id;
    String firstName;
    String lastName;
    String email;
    String roleName;

    Boolean isNonLocked;
    Boolean isEnabled;
}

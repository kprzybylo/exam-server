package pl.edu.prz.ai.exam.users.application.request;

import lombok.Value;
import pl.edu.prz.ai.exam.users.application.validation.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
public class CreateUser {
    @NotNull @NotEmpty @Email @UniqueEmail
    String email;

    @NotNull @NotEmpty
    String firstName;

    @NotNull @NotEmpty
    String lastName;

    @NotNull @NotEmpty
    String password;
}

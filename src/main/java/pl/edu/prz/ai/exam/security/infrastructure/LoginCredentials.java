package pl.edu.prz.ai.exam.security.infrastructure;

import lombok.Value;

@Value
class LoginCredentials {
    String username;
    String password;
}

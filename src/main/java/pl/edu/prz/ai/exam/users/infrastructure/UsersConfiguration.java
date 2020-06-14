package pl.edu.prz.ai.exam.users.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.edu.prz.ai.exam.users.domain.service.DomainUsersService;
import pl.edu.prz.ai.exam.users.domain.service.UsersService;

@Configuration
public class UsersConfiguration {
    @Bean
    UsersService usersService(
            SpringUserRepository springUserRepository,
            SpringRoleRepository springRoleRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        return new DomainUsersService(springUserRepository, springRoleRepository, bCryptPasswordEncoder);
    }
}

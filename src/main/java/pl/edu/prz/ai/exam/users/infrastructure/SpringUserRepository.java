package pl.edu.prz.ai.exam.users.infrastructure;

import org.springframework.data.repository.Repository;
import pl.edu.prz.ai.exam.users.domain.User;
import pl.edu.prz.ai.exam.users.domain.repository.UserRepository;

public interface SpringUserRepository extends UserRepository, Repository<User, Long> {
}

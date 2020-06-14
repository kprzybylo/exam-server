package pl.edu.prz.ai.exam.groups.infrastructure;

import org.springframework.data.repository.Repository;
import pl.edu.prz.ai.exam.groups.domain.User;
import pl.edu.prz.ai.exam.groups.domain.repository.GroupUserRepository;

public interface SpringGroupUserRepository extends GroupUserRepository, Repository<User, Long> {
}

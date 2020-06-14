package pl.edu.prz.ai.exam.users.infrastructure;

import org.springframework.data.repository.Repository;
import pl.edu.prz.ai.exam.users.domain.Role;
import pl.edu.prz.ai.exam.users.domain.repository.RoleRepository;

public interface SpringRoleRepository extends RoleRepository, Repository<Role, Long> {
}

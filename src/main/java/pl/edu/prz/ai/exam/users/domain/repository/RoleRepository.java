package pl.edu.prz.ai.exam.users.domain.repository;

import pl.edu.prz.ai.exam.users.domain.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByRoleName(String roleName);
}

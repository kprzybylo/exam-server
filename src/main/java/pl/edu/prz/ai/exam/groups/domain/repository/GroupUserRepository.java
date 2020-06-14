package pl.edu.prz.ai.exam.groups.domain.repository;

import pl.edu.prz.ai.exam.groups.domain.User;

import java.util.Optional;

public interface GroupUserRepository {
    Optional<User> findById(Long id);

    User findByEmail(String username);
}

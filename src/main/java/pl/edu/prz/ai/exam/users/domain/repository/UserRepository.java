package pl.edu.prz.ai.exam.users.domain.repository;

import pl.edu.prz.ai.exam.users.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    void save(User user);
}

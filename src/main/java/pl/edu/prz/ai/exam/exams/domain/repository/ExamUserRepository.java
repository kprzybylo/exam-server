package pl.edu.prz.ai.exam.exams.domain.repository;

import pl.edu.prz.ai.exam.exams.domain.User;

import java.util.Optional;

public interface ExamUserRepository {
    Optional<User> findByEmail(String email);
}

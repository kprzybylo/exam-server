package pl.edu.prz.ai.exam.exams.domain.repository;

import pl.edu.prz.ai.exam.exams.domain.Group;

import java.util.Optional;

public interface GroupRepository {
    Optional<Group> findById(Long id);
}

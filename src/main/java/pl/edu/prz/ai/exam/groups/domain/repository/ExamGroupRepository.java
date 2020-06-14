package pl.edu.prz.ai.exam.groups.domain.repository;

import pl.edu.prz.ai.exam.groups.domain.ExamGroup;

import java.util.Optional;

public interface ExamGroupRepository {
    Optional<ExamGroup> findById(Long id);
    Optional<ExamGroup> findByGroupName(String groupName);

    void save(ExamGroup examGroup);
}

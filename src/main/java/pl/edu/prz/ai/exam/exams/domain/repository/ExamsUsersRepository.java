package pl.edu.prz.ai.exam.exams.domain.repository;

import pl.edu.prz.ai.exam.exams.domain.ExamsUsers;

import java.util.Optional;

public interface ExamsUsersRepository {
    void save(ExamsUsers examsUsers);

    Optional<ExamsUsers> findByExam_IdAndUser_Id(Long examId, Long userId);
}

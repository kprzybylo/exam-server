package pl.edu.prz.ai.exam.exams.domain.repository;

import pl.edu.prz.ai.exam.exams.domain.Exam;

import java.util.Optional;

public interface ExamRepository {
    Exam findById(Long id);

    void save(Exam exam);
}

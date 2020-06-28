package pl.edu.prz.ai.exam.exams.domain.repository;

import pl.edu.prz.ai.exam.exams.domain.Question;

import java.util.Optional;

public interface QuestionRepository {
    Question findById(Long questionId);

    void save(Question question);
}

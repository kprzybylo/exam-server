package pl.edu.prz.ai.exam.exams.domain.repository;

import pl.edu.prz.ai.exam.exams.domain.Answer;

public interface AnswerRepository {
    void save(Answer answer);

    Answer findById(Long id);

    void deleteById(Long answerId);
}

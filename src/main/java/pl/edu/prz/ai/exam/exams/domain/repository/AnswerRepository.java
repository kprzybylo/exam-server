package pl.edu.prz.ai.exam.exams.domain.repository;

import pl.edu.prz.ai.exam.exams.domain.Answer;

import java.util.List;

public interface AnswerRepository {
    void save(Answer answer);

    Answer findById(Long id);

    List<Answer> findAllByQuestion_Id(Long questionId);

    void deleteById(Long answerId);
}

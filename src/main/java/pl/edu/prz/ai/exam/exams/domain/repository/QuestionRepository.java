package pl.edu.prz.ai.exam.exams.domain.repository;

import pl.edu.prz.ai.exam.exams.domain.Question;

import java.util.Optional;

import java.util.List;

public interface QuestionRepository {
    Question findById(Long questionId);

    void save(Question question);

    List<Question> findAllByExam_Id(Long examId);
}

package pl.edu.prz.ai.exam.exams.domain.repository;

import pl.edu.prz.ai.exam.exams.domain.UsersAnswers;

import java.util.Optional;

public interface UsersAnswersRepository {
    Optional<UsersAnswers> findByQuestion_IdAndUser_Id(Long questionId, Long userId);

    void save(UsersAnswers usersAnswers);
}

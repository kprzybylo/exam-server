package pl.edu.prz.ai.exam.exams.infrastructure;

import org.springframework.data.repository.Repository;
import pl.edu.prz.ai.exam.exams.domain.UsersAnswers;
import pl.edu.prz.ai.exam.exams.domain.repository.UsersAnswersRepository;

public interface SpringUsersAnswersRepository extends UsersAnswersRepository, Repository<UsersAnswers, Long> {
}

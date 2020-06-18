package pl.edu.prz.ai.exam.exams.infrastructure;

import org.springframework.data.repository.Repository;
import pl.edu.prz.ai.exam.exams.domain.Answer;
import pl.edu.prz.ai.exam.exams.domain.repository.AnswerRepository;

public interface SpringAnswerRepository extends AnswerRepository, Repository<Answer, Long> {
}

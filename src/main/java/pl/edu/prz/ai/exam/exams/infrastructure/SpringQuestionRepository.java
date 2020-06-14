package pl.edu.prz.ai.exam.exams.infrastructure;

import org.springframework.data.repository.Repository;
import pl.edu.prz.ai.exam.exams.domain.Question;
import pl.edu.prz.ai.exam.exams.domain.repository.QuestionRepository;

public interface SpringQuestionRepository extends QuestionRepository, Repository<Question, Long> {
}

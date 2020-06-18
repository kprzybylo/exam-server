package pl.edu.prz.ai.exam.exams.infrastructure;

import org.springframework.data.repository.Repository;
import pl.edu.prz.ai.exam.exams.domain.User;
import pl.edu.prz.ai.exam.exams.domain.repository.ExamUserRepository;

public interface SpringExamUserRepository extends ExamUserRepository, Repository<User, Long> {
}

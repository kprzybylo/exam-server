package pl.edu.prz.ai.exam.exams.infrastructure;

import org.springframework.data.repository.Repository;
import pl.edu.prz.ai.exam.exams.domain.Exam;
import pl.edu.prz.ai.exam.exams.domain.repository.ExamRepository;

public interface SpringExamRepository extends ExamRepository, Repository<Exam, Long> {
}

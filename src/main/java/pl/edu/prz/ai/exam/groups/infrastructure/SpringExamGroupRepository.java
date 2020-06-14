package pl.edu.prz.ai.exam.groups.infrastructure;

import org.springframework.data.repository.Repository;
import pl.edu.prz.ai.exam.groups.domain.ExamGroup;
import pl.edu.prz.ai.exam.groups.domain.repository.ExamGroupRepository;

public interface SpringExamGroupRepository extends ExamGroupRepository, Repository<ExamGroup, Long> {
}

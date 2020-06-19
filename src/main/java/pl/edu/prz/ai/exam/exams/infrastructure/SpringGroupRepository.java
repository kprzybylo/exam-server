package pl.edu.prz.ai.exam.exams.infrastructure;

import org.springframework.data.repository.Repository;
import pl.edu.prz.ai.exam.exams.domain.Group;
import pl.edu.prz.ai.exam.exams.domain.repository.GroupRepository;

public interface SpringGroupRepository extends GroupRepository, Repository<Group, Long> {
}

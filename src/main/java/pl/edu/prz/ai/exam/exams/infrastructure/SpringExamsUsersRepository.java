package pl.edu.prz.ai.exam.exams.infrastructure;

import org.springframework.data.repository.Repository;
import pl.edu.prz.ai.exam.exams.domain.ExamsUsers;
import pl.edu.prz.ai.exam.exams.domain.repository.ExamsUsersRepository;

public interface SpringExamsUsersRepository extends ExamsUsersRepository, Repository<ExamsUsers, Long> {
}

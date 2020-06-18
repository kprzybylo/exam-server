package pl.edu.prz.ai.exam.exams.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.prz.ai.exam.exams.domain.service.DomainExamsService;
import pl.edu.prz.ai.exam.exams.domain.service.ExamsService;

@Configuration
public class ExamsConfiguration {
    @Bean
    public ExamsService examsService(
            SpringExamRepository springExamRepository,
            SpringQuestionRepository springQuestionRepository,
            SpringAnswerRepository springAnswerRepository,
            SpringExamUserRepository springExamUserRepository) {
        return new DomainExamsService(
                springExamRepository,
                springQuestionRepository,
                springAnswerRepository,
                springExamUserRepository
        );
    }
}

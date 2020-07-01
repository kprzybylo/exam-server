package pl.edu.prz.ai.exam.exams.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.prz.ai.exam.exams.domain.repository.*;
import pl.edu.prz.ai.exam.exams.domain.service.*;

@Configuration
public class ExamsConfiguration {
    @Bean
    public AppUsersService appUsersService(
            SpringAppUserRepository appUserRepository,
            SpringGroupRepository groupRepository) {
        return new DomainAppUsersService(appUserRepository, groupRepository);
    }
    @Bean
    public ExamsService examsService(
            SpringExamRepository examRepository,
            SpringExamsUsersRepository examsUsersRepository,
            AppUsersService appUsersService,
            QuestionsService questionsService) {
        return new DomainExamsService(
                examRepository,
                examsUsersRepository,
                appUsersService,
                questionsService
        );
    }

    @Bean
    public QuestionsService questionsService(
            SpringQuestionRepository questionRepository,
            SpringAnswerRepository answerRepository,
            SpringAttachmentRepository attachmentRepository,
            SpringUsersAnswersRepository springUsersAnswersRepository) {
        return new DomainQuestionsService(
                questionRepository,
                answerRepository,
                attachmentRepository,
                springUsersAnswersRepository
        );
    }
}

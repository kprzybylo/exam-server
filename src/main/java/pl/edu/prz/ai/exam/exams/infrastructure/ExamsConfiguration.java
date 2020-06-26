package pl.edu.prz.ai.exam.exams.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.prz.ai.exam.exams.domain.repository.*;
import pl.edu.prz.ai.exam.exams.domain.service.*;

@Configuration
public class ExamsConfiguration {
    @Bean
    public AppUsersService appUsersService(
            AppUserRepository appUserRepository,
            GroupRepository groupRepository) {
        return new DomainAppUsersService(appUserRepository, groupRepository);
    }
    @Bean
    public ExamsService examsService(
            ExamRepository examRepository,
            ExamsUsersRepository examsUsersRepository,
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
            QuestionRepository questionRepository,
            AnswerRepository answerRepository,
            AttachmentRepository attachmentRepository) {
        return new DomainQuestionsService(
                questionRepository,
                answerRepository,
                attachmentRepository
        );
    }
}

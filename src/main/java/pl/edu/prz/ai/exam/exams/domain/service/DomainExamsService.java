package pl.edu.prz.ai.exam.exams.domain.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import pl.edu.prz.ai.exam.exams.application.request.CreateExam;
import pl.edu.prz.ai.exam.exams.application.request.CreateQuestion;
import pl.edu.prz.ai.exam.exams.application.response.CreatedExam;
import pl.edu.prz.ai.exam.exams.domain.Answer;
import pl.edu.prz.ai.exam.exams.domain.Exam;
import pl.edu.prz.ai.exam.exams.domain.Question;
import pl.edu.prz.ai.exam.exams.domain.User;
import pl.edu.prz.ai.exam.exams.domain.exception.CouldNotAcquireLoggedUserException;
import pl.edu.prz.ai.exam.exams.domain.exception.ExamNotFoundException;
import pl.edu.prz.ai.exam.exams.domain.exception.OperationNotAllowedException;
import pl.edu.prz.ai.exam.exams.domain.repository.AnswerRepository;
import pl.edu.prz.ai.exam.exams.domain.repository.ExamRepository;
import pl.edu.prz.ai.exam.exams.domain.repository.ExamUserRepository;
import pl.edu.prz.ai.exam.exams.domain.repository.QuestionRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DomainExamsService implements ExamsService {
    ExamRepository examRepository;
    QuestionRepository questionRepository;
    AnswerRepository answerRepository;
    ExamUserRepository examUserRepository;

    ExamsMapper examsMapper = new ExamsMapper();
    QuestionsMapper questionsMapper = new QuestionsMapper();

    @Override
    public CreatedExam createNewExam(CreateExam createExam) {
        Exam exam = examsMapper.toExam(createExam);


        User creator = examUserRepository.findByEmail(getLoggedUserEmail())
                .orElseThrow(CouldNotAcquireLoggedUserException::new);

        exam = exam.toBuilder()
                .creator(creator)
                .build();

        examRepository.save(exam);

        return examsMapper.toCreatedExam(exam);
    }

    @Override
    public void addQuestionToExam(Long examId, CreateQuestion createQuestion) {
        User creator = examUserRepository.findByEmail(getLoggedUserEmail())
                .orElseThrow(CouldNotAcquireLoggedUserException::new);

        Exam exam = examRepository.findById(examId)
                .orElseThrow(ExamNotFoundException::new);

        if (!exam.getCreator().equals(creator)) {
            throw new OperationNotAllowedException();
        }

        Question question = questionsMapper.toQuestion(createQuestion)
                .toBuilder()
                .exam(exam)
                .build();

        questionRepository.save(question);

        if(!question.getIsOpenQuestion()) {
            List<Answer> answers = createQuestion.getPossibleAnswers()
                    .stream()
                    .map(createAnswer -> Answer.builder()
                            .answerText(createAnswer.getAnswerText())
                            .isCorrectAnswer(createAnswer.getIsCorrectAnswer())
                            .question(question)
                            .build())
                    .collect(Collectors.toList());
            answers.forEach(answerRepository::save);
        }
    }

    private String getLoggedUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return username;
    }
}

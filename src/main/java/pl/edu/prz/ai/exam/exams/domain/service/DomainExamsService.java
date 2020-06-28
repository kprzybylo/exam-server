package pl.edu.prz.ai.exam.exams.domain.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.prz.ai.exam.exams.application.request.AssignGroup;
import pl.edu.prz.ai.exam.exams.application.request.AssignUser;
import pl.edu.prz.ai.exam.exams.application.request.CreateQuestion;
import pl.edu.prz.ai.exam.exams.application.request.ExamRequest;
import pl.edu.prz.ai.exam.exams.application.response.ExamResponse;
import pl.edu.prz.ai.exam.exams.domain.*;
import pl.edu.prz.ai.exam.exams.domain.exception.CannotChangeActiveExamException;
import pl.edu.prz.ai.exam.exams.domain.exception.OperationNotAllowedException;
import pl.edu.prz.ai.exam.exams.domain.repository.ExamRepository;
import pl.edu.prz.ai.exam.exams.domain.repository.ExamsUsersRepository;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DomainExamsService implements ExamsService {
    ExamRepository examRepository;
    ExamsUsersRepository examsUsersRepository;

    AppUsersService appUsersService;
    QuestionsService questionsService;

    ExamsMapper examsMapper = new ExamsMapper();

    @Override
    public ExamResponse createNewExam(ExamRequest examRequest) {
        Exam exam = examsMapper.toExam(examRequest);

        exam = exam.toBuilder()
                .creator(appUsersService.getLoggedUser())
                .build();

        examRepository.save(exam);

        return examsMapper.toCreatedExam(exam);
    }

    @Override
    public ExamResponse updateExistingExam(Long examId, ExamRequest examRequest) {
        Exam exam = examRepository.findById(examId);

        if (exam.getIsActive()) {
            throw new CannotChangeActiveExamException();
        }

        exam = exam.toBuilder()
                .examName(examRequest.getExamName())
                .availableTime(examRequest.getAvailableTimeInMinutes())
                .validFrom(examRequest.getAvailableFrom())
                .validTo(examRequest.getAvailableTo())
                .build();

        examRepository.save(exam);

        return examsMapper.toCreatedExam(exam);
    }

    @Override
    public Question addQuestionToExam(Long examId, CreateQuestion createQuestion) {
        this.checkIfUserIsOwner(examId);

        return questionsService.createQuestion(
                examRepository.findById(examId),
                createQuestion
        );
    }

    @Override
    public void assignGroupToExam(Long examId, AssignGroup assignGroup) {
        Group group = appUsersService.findGroup(assignGroup.getGroupId());

        Exam exam = examRepository.findById(examId);

        group.getStudents()
                .forEach(user -> addUserToExam(user, exam));
    }

    @Override
    public void assignUserToExam(Long examId, AssignUser assignUser) {
        User user = appUsersService.findUser(assignUser.getUserId());

        Exam exam = examRepository.findById(examId);

        addUserToExam(user, exam);
    }

    @Override
    public void addAttachmentToQuestion(Question question, MultipartFile file) {
        questionsService.addAttachmentToQuestion(question, file);
    }

    private void addUserToExam(User user, Exam exam) {
        ExamsUsers examsUsers = ExamsUsers.builder()
                .user(user)
                .exam(exam)
                .build();

        examsUsersRepository.save(examsUsers);
    }

    @Override
    public void checkIfUserIsOwner(Long examId) {
        if (!examRepository.findById(examId).getCreator().equals(appUsersService.getLoggedUser())) {
            throw new OperationNotAllowedException();
        }
    }
}

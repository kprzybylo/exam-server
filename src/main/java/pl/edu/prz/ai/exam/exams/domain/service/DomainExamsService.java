package pl.edu.prz.ai.exam.exams.domain.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.prz.ai.exam.exams.application.request.*;
import pl.edu.prz.ai.exam.exams.application.response.ExamResponse;
import pl.edu.prz.ai.exam.exams.domain.*;
import pl.edu.prz.ai.exam.exams.domain.exception.CannotChangeActiveExamException;
import pl.edu.prz.ai.exam.exams.domain.exception.OperationNotAllowedException;
import pl.edu.prz.ai.exam.exams.domain.repository.ExamRepository;
import pl.edu.prz.ai.exam.exams.domain.repository.ExamsUsersRepository;

import java.util.Calendar;
import java.util.List;

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

    @Override
    public void startExam(Long examId, StartExam startExam) {
        checkIfUserIsOwner(examId);

        Exam exam = examRepository.findById(examId);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, startExam.getAvailabilityInHours());

        exam = exam.toBuilder()
                .isActive(true)
                .validTo(calendar.getTime())
                .build();

        examRepository.save(exam);
    }

    @Scheduled(fixedDelay = 60000)
    public void startExams() {
        List<Exam> examsToStart = examRepository.findAllNotStartedExams();

        examsToStart.forEach(exam -> {
            Exam startingExam = exam.toBuilder()
                    .isActive(true)
                    .build();

            examRepository.save(startingExam);
        });
    }

    @Scheduled(fixedDelay = 60000)
    public void stopExams() {
        List<Exam> examsToStop = examRepository.findAllExpiredExams();

        examsToStop.forEach(exam -> {
            Exam startingExam = exam.toBuilder()
                    .isActive(false)
                    .build();

            examRepository.save(startingExam);
        });
    }
}

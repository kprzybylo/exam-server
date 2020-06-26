package pl.edu.prz.ai.exam.exams.domain.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.prz.ai.exam.exams.application.request.AssignGroup;
import pl.edu.prz.ai.exam.exams.application.request.AssignUser;
import pl.edu.prz.ai.exam.exams.application.request.CreateExam;
import pl.edu.prz.ai.exam.exams.application.request.CreateQuestion;
import pl.edu.prz.ai.exam.exams.application.response.CreatedExam;
import pl.edu.prz.ai.exam.exams.domain.*;
import pl.edu.prz.ai.exam.exams.domain.exception.ExamNotFoundException;
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
    public CreatedExam createNewExam(CreateExam createExam) {
        Exam exam = examsMapper.toExam(createExam);

        exam = exam.toBuilder()
                .creator(appUsersService.getLoggedUser())
                .build();

        examRepository.save(exam);

        return examsMapper.toCreatedExam(exam);
    }

    @Override
    public Question addQuestionToExam(Long examId, CreateQuestion createQuestion) {
        User creator = appUsersService.getLoggedUser();

        Exam exam = examRepository.findById(examId)
                .orElseThrow(ExamNotFoundException::new);

        if (!exam.getCreator().equals(creator)) {
            throw new OperationNotAllowedException();
        }

        return questionsService.createQuestion(exam, createQuestion);
    }

    @Override
    public void assignGroupToExam(Long examId, AssignGroup assignGroup) {
        Group group = appUsersService.findGroup(assignGroup.getGroupId());

        Exam exam = examRepository.findById(examId)
                .orElseThrow(ExamNotFoundException::new);

        group.getStudents()
                .forEach(user -> addUserToExam(user, exam));
    }

    @Override
    public void assignUserToExam(Long examId, AssignUser assignUser) {
        User user = appUsersService.findUser(assignUser.getUserId());

        Exam exam = examRepository.findById(examId)
                .orElseThrow(ExamNotFoundException::new);

        addUserToExam(user, exam);
    }

    @Override
    public void addAttachmentToQuestion(Question question, MultipartFile file) {
        questionsService.addAttachmentToExistingQuestion(question, file);
    }

    private void addUserToExam(User user, Exam exam) {
        ExamsUsers examsUsers = ExamsUsers.builder()
                .user(user)
                .exam(exam)
                .build();

        examsUsersRepository.save(examsUsers);
    }
}

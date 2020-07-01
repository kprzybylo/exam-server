package pl.edu.prz.ai.exam.exams.domain.service;

import org.springframework.web.multipart.MultipartFile;
import pl.edu.prz.ai.exam.exams.application.request.*;
import pl.edu.prz.ai.exam.exams.application.response.ExamQuestion;
import pl.edu.prz.ai.exam.exams.application.response.ExamResponse;
import pl.edu.prz.ai.exam.exams.domain.Question;

import java.util.List;

public interface ExamsService {
    ExamResponse createNewExam(ExamRequest examRequest);

    Question addQuestionToExam(Long examId, CreateQuestion createQuestion);

    void assignGroupToExam(Long examId, AssignGroup assignGroup);

    void assignUserToExam(Long examId, AssignUser assignUser);

    void addAttachmentToQuestion(Question question, MultipartFile file);

    ExamResponse updateExistingExam(Long examId, ExamRequest examRequest);

    void checkIfUserIsOwner(Long examId);

    void activateExam(Long examId, ActivateExam activateExam);

    List<ExamQuestion> startSolvingExam(Long examId);
}

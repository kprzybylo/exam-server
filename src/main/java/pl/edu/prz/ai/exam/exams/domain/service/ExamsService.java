package pl.edu.prz.ai.exam.exams.domain.service;

import org.springframework.web.multipart.MultipartFile;
import pl.edu.prz.ai.exam.exams.application.request.AssignGroup;
import pl.edu.prz.ai.exam.exams.application.request.AssignUser;
import pl.edu.prz.ai.exam.exams.application.request.CreateExam;
import pl.edu.prz.ai.exam.exams.application.request.CreateQuestion;
import pl.edu.prz.ai.exam.exams.application.response.CreatedExam;
import pl.edu.prz.ai.exam.exams.domain.Question;

public interface ExamsService {
    CreatedExam createNewExam(CreateExam createExam);

    Question addQuestionToExam(Long examId, CreateQuestion createQuestion);

    void assignGroupToExam(Long examId, AssignGroup assignGroup);

    void assignUserToExam(Long examId, AssignUser assignUser);

    void addAttachmentToQuestion(Question question, MultipartFile file);
}

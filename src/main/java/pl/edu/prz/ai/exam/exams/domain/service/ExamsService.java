package pl.edu.prz.ai.exam.exams.domain.service;

import pl.edu.prz.ai.exam.exams.application.request.AssignGroup;
import pl.edu.prz.ai.exam.exams.application.request.AssignUser;
import pl.edu.prz.ai.exam.exams.application.request.CreateExam;
import pl.edu.prz.ai.exam.exams.application.request.CreateQuestion;
import pl.edu.prz.ai.exam.exams.application.response.CreatedExam;

public interface ExamsService {
    CreatedExam createNewExam(CreateExam createExam);

    void addQuestionToExam(Long examId, CreateQuestion createQuestion);

    void assignGroupToExam(Long examId, AssignGroup assignGroup);

    void assignUserToExam(Long examId, AssignUser assignUser);
}

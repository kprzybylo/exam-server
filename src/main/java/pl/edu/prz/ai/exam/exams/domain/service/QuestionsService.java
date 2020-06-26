package pl.edu.prz.ai.exam.exams.domain.service;

import org.springframework.web.multipart.MultipartFile;
import pl.edu.prz.ai.exam.exams.application.request.CreateQuestion;
import pl.edu.prz.ai.exam.exams.domain.Exam;
import pl.edu.prz.ai.exam.exams.domain.Question;

public interface QuestionsService {
    Question createQuestion(Exam exam, CreateQuestion createQuestion);

    void addAttachmentToExistingQuestion(Question question, MultipartFile multipartFile);
}

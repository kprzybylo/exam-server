package pl.edu.prz.ai.exam.exams.domain.service;

import org.springframework.web.multipart.MultipartFile;
import pl.edu.prz.ai.exam.exams.application.request.AnswerRequest;
import pl.edu.prz.ai.exam.exams.application.request.CreateQuestion;
import pl.edu.prz.ai.exam.exams.domain.Exam;
import pl.edu.prz.ai.exam.exams.domain.Question;

public interface QuestionsService {
    Question createQuestion(Exam exam, CreateQuestion createQuestion);

    void addAttachmentToQuestion(Question question, MultipartFile multipartFile);

    void replaceAttachmentForQuestion(Long examId, Long questionId, MultipartFile file);

    void addAnswerToQuestion(Long examId, Long questionId, AnswerRequest answerRequest);

    void editAnswerOfQuestion(Long examId, Long questionId, Long answerId, AnswerRequest answerRequest);

    void deleteAnswerFromQuestion(Long examId, Long questionId, Long answerId);

    void checkIfQuestionBelongsToExam(Long examId, Long questionId);

    void removeAttachmentFromQuestion(Long examId, Long questionId);
}

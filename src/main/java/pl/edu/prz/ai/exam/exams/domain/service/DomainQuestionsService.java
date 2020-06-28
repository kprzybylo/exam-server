package pl.edu.prz.ai.exam.exams.domain.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.prz.ai.exam.exams.application.request.AnswerRequest;
import pl.edu.prz.ai.exam.exams.application.request.CreateQuestion;
import pl.edu.prz.ai.exam.exams.domain.Answer;
import pl.edu.prz.ai.exam.exams.domain.Attachment;
import pl.edu.prz.ai.exam.exams.domain.Exam;
import pl.edu.prz.ai.exam.exams.domain.Question;
import pl.edu.prz.ai.exam.exams.domain.exception.InconsistentDataProvidedException;
import pl.edu.prz.ai.exam.exams.domain.exception.SavingAttachmentFailedException;
import pl.edu.prz.ai.exam.exams.domain.repository.AnswerRepository;
import pl.edu.prz.ai.exam.exams.domain.repository.AttachmentRepository;
import pl.edu.prz.ai.exam.exams.domain.repository.QuestionRepository;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DomainQuestionsService implements QuestionsService {
    @Value("${app.config.attachments.directory}")
    @NonFinal String attachmentsDirectory;

    QuestionRepository questionRepository;
    AnswerRepository answerRepository;
    AttachmentRepository attachmentRepository;

    QuestionsMapper questionsMapper = new QuestionsMapper();

    @Override
    public Question createQuestion(Exam exam, CreateQuestion createQuestion) {
        Question question = questionsMapper.toQuestion(createQuestion)
                .toBuilder()
                .exam(exam)
                .build();

        questionRepository.save(question);

        if (!question.getIsOpenQuestion()) {
            this.saveQuestionAnswers(question, createQuestion.getPossibleAnswers());
        }

        return question;
    }

    @Override
    public void addAttachmentToQuestion(Question question, MultipartFile multipartFile) {
        try {
            String fileLocation = saveFileToServer(multipartFile);

            Attachment attachment = Attachment.builder()
                    .question(question)
                    .attachmentLocation(fileLocation)
                    .build();

            attachmentRepository.save(attachment);
        } catch (IOException e) {
            throw new SavingAttachmentFailedException();
        }
    }

    @Override
    public void replaceAttachmentForQuestion(Long examId, Long questionId, MultipartFile multipartFile) {
        checkIfQuestionBelongsToExam(examId, questionId);

        try {
            Attachment attachment = attachmentRepository.findByQuestion_Id(questionId);

            String fileLocation = saveFileToServer(multipartFile);

            if (attachment != null) {
                attachment = attachment.toBuilder()
                        .attachmentLocation(fileLocation)
                        .build();

                attachmentRepository.save(attachment);
            } else {
                addAttachmentToQuestion(questionRepository.findById(questionId), multipartFile);
            }
        } catch (IOException e) {
            throw new SavingAttachmentFailedException();
        }
    }

    @Override
    public void addAnswerToQuestion(Long examId, Long questionId, AnswerRequest answerRequest) {
        checkIfQuestionBelongsToExam(examId, questionId);

        Question question = questionRepository.findById(questionId);

        saveQuestionAnswers(question, List.of(answerRequest));
    }

    @Override
    public void editAnswerOfQuestion(Long examId, Long questionId, Long answerId, AnswerRequest answerRequest) {
        checkIfQuestionBelongsToExam(examId, questionId);

        Answer answer = answerRepository.findById(answerId);

        answer = answer.toBuilder()
                .isCorrectAnswer(answerRequest.getIsCorrectAnswer())
                .answerText(answerRequest.getAnswerText())
                .build();

        answerRepository.save(answer);
    }

    @Override
    public void deleteAnswerFromQuestion(Long examId, Long questionId, Long answerId) {
        checkIfQuestionBelongsToExam(examId, questionId);

        answerRepository.deleteById(answerId);
    }

    @Override
    public void removeAttachmentFromQuestion(Long examId, Long questionId) {
        checkIfQuestionBelongsToExam(examId, questionId);

        Attachment attachment = attachmentRepository.findByQuestion_Id(questionId);

        attachmentRepository.delete(attachment);
    }

    @Override
    public void checkIfQuestionBelongsToExam(Long examId, Long questionId) {
        Question question = questionRepository.findById(questionId);

        if (!question.getExam().getId().equals(examId)) {
            throw new InconsistentDataProvidedException();
        }
    }

    private void saveQuestionAnswers(Question question, List<AnswerRequest> possibleAnswers) {
        possibleAnswers.stream()
                .map(answerRequest -> Answer.builder()
                        .answerText(answerRequest.getAnswerText())
                        .isCorrectAnswer(answerRequest.getIsCorrectAnswer())
                        .question(question)
                        .build())
                .collect(Collectors.toList())
                .forEach(answerRepository::save);
    }

    private String saveFileToServer(MultipartFile multipartFile) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
        String fileName = attachmentsDirectory + "/"
                + simpleDateFormat.format(new Date())
                + "_" + multipartFile.getOriginalFilename();
        File file = new File(fileName);
        multipartFile.transferTo(file);
        return fileName;
    }
}

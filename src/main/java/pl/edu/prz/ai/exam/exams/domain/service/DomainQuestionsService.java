package pl.edu.prz.ai.exam.exams.domain.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.prz.ai.exam.exams.application.request.CreateAnswer;
import pl.edu.prz.ai.exam.exams.application.request.CreateQuestion;
import pl.edu.prz.ai.exam.exams.domain.Answer;
import pl.edu.prz.ai.exam.exams.domain.Attachment;
import pl.edu.prz.ai.exam.exams.domain.Exam;
import pl.edu.prz.ai.exam.exams.domain.Question;
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
    public void addAttachmentToExistingQuestion(Question question, MultipartFile multipartFile) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
            String fileName = attachmentsDirectory + "/"
                    + simpleDateFormat.format(new Date())
                    + "_" + multipartFile.getOriginalFilename();
            File file = new File(fileName);
            multipartFile.transferTo(file);

            Attachment attachment = Attachment.builder()
                    .question(question)
                    .attachmentLocation(fileName)
                    .build();

            attachmentRepository.save(attachment);
        } catch (IOException e) {
            throw new SavingAttachmentFailedException();
        }
    }

    private void saveQuestionAnswers(Question question, List<CreateAnswer> possibleAnswers) {
        possibleAnswers.stream()
                .map(createAnswer -> Answer.builder()
                        .answerText(createAnswer.getAnswerText())
                        .isCorrectAnswer(createAnswer.getIsCorrectAnswer())
                        .question(question)
                        .build())
                .collect(Collectors.toList())
                .forEach(answerRepository::save);
    }
}

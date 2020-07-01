package pl.edu.prz.ai.exam.exams.application.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.prz.ai.exam.exams.application.request.AnswerRequest;
import pl.edu.prz.ai.exam.exams.application.request.CreateQuestion;
import pl.edu.prz.ai.exam.exams.application.request.SubmitAnswer;
import pl.edu.prz.ai.exam.exams.application.validation.ExistingAnswer;
import pl.edu.prz.ai.exam.exams.application.validation.ExistingExam;
import pl.edu.prz.ai.exam.exams.application.validation.ExistingQuestion;
import pl.edu.prz.ai.exam.exams.domain.Question;
import pl.edu.prz.ai.exam.exams.domain.service.AppUsersService;
import pl.edu.prz.ai.exam.exams.domain.service.ExamsService;
import pl.edu.prz.ai.exam.exams.domain.service.QuestionsService;

import javax.validation.Valid;

@Validated
@Controller
@RequestMapping("/exams/{examId}/questions")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionsController {
    ExamsService examsService;
    QuestionsService questionsService;
    AppUsersService appUsersService;
    ObjectMapper objectMapper;

    @PostMapping(consumes = {"multipart/form-data"})
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> addQuestionToExam(
            @Valid @ExistingExam @PathVariable("examId") Long examId,
            @RequestParam("createQuestion") String createQuestion,
            @RequestParam("file") MultipartFile file) throws JsonProcessingException {
        CreateQuestion converted = objectMapper.readValue(createQuestion, CreateQuestion.class);
        Question question = examsService.addQuestionToExam(examId, converted);
        examsService.addAttachmentToQuestion(question, file);

        return ResponseEntity.ok().build();
    }

    @PostMapping(
            value = "/{questionId}/attachments",
            consumes = {"multipart/form-data"})
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> addAttachmentToQuestion(
            @Valid @ExistingExam @PathVariable("examId") Long examId,
            @Valid @ExistingQuestion @PathVariable("questionId") Long questionId,
            @RequestParam("file") MultipartFile file) {
        examsService.checkIfUserIsOwner(examId);

        questionsService.replaceAttachmentForQuestion(examId, questionId, file);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{questionId}/attachments")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> removeAttachmentFromQuestion(
            @Valid @ExistingExam @PathVariable("examId") Long examId,
            @Valid @ExistingQuestion @PathVariable("questionId") Long questionId) {
        examsService.checkIfUserIsOwner(examId);

        questionsService.removeAttachmentFromQuestion(examId, questionId);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{questionId}/answers")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> addAnswerToQuestion(
            @Valid @ExistingExam @PathVariable("examId") Long examId,
            @Valid @ExistingQuestion @PathVariable("questionId") Long questionId,
            @Valid @RequestBody AnswerRequest answerRequest) {
        examsService.checkIfUserIsOwner(examId);

        questionsService.addAnswerToQuestion(examId, questionId, answerRequest);

        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{questionId}/answers/{answerId}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> editAnswerOfQuestion(
            @Valid @ExistingExam @PathVariable("examId") Long examId,
            @Valid @ExistingQuestion @PathVariable("questionId") Long questionId,
            @Valid @ExistingAnswer @PathVariable("answerId") Long answerId,
            @Valid @RequestBody AnswerRequest answerRequest) {
        examsService.checkIfUserIsOwner(examId);

        questionsService.editAnswerOfQuestion(examId, questionId, answerId, answerRequest);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{questionId}/answers/{answerId}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> deleteAnswerFromQuestion(
            @Valid @ExistingExam @PathVariable("examId") Long examId,
            @Valid @ExistingQuestion @PathVariable("questionId") Long questionId,
            @Valid @ExistingAnswer @PathVariable("answerId") Long answerId) {
        examsService.checkIfUserIsOwner(examId);

        questionsService.deleteAnswerFromQuestion(examId, questionId, answerId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{questionId}/submitAnswer")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> submitAnswerToQuestion(
            @Valid @ExistingExam @PathVariable("examId") Long examId,
            @Valid @ExistingQuestion @PathVariable("questionId") Long questionId,
            @Valid @RequestBody SubmitAnswer submitAnswer) {
        questionsService.submitAnswerToQuestion(
                examId, questionId,
                appUsersService.getLoggedUser(),
                submitAnswer);

        return ResponseEntity.ok().build();
    }
}

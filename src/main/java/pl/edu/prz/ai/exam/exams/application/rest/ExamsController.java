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
import pl.edu.prz.ai.exam.exams.application.request.AssignGroup;
import pl.edu.prz.ai.exam.exams.application.request.AssignUser;
import pl.edu.prz.ai.exam.exams.application.request.ExamRequest;
import pl.edu.prz.ai.exam.exams.application.request.CreateQuestion;
import pl.edu.prz.ai.exam.exams.application.response.ExamResponse;
import pl.edu.prz.ai.exam.exams.application.validation.ExistingExam;
import pl.edu.prz.ai.exam.exams.domain.Question;
import pl.edu.prz.ai.exam.exams.domain.service.ExamsService;

import javax.validation.Valid;

@Validated
@Controller
@RequestMapping("/exams")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamsController {
    ExamsService examsService;
    ObjectMapper objectMapper;

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<ExamResponse> createNewExam(@Valid @RequestBody ExamRequest examRequest) {
        return ResponseEntity.ok(examsService.createNewExam(examRequest));
    }

    @PatchMapping("/{examId}")
    public ResponseEntity<ExamResponse> editExistingExam(
            @Valid @ExistingExam @PathVariable("examId") Long examId,
            @Valid @RequestBody ExamRequest examRequest
    ) {
        return ResponseEntity.ok(examsService.updateExistingExam(examId, examRequest));
    }

    @PostMapping(value = "/{examId}/questions", consumes = {"multipart/form-data"})
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

    @PostMapping("/{examId}/groups")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> assignGroupToExam(
            @Valid @ExistingExam @PathVariable("examId") Long examId,
            @Valid @RequestBody AssignGroup assignGroup){
        examsService.assignGroupToExam(examId, assignGroup);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{examId}/users")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> assignUserToExam(
            @Valid @ExistingExam @PathVariable("examId") Long examId,
            @Valid @RequestBody AssignUser assignUser) {
        examsService.assignUserToExam(examId, assignUser);

        return ResponseEntity.ok().build();
    }
}

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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.prz.ai.exam.exams.application.request.AssignGroup;
import pl.edu.prz.ai.exam.exams.application.request.AssignUser;
import pl.edu.prz.ai.exam.exams.application.request.CreateExam;
import pl.edu.prz.ai.exam.exams.application.request.CreateQuestion;
import pl.edu.prz.ai.exam.exams.application.response.CreatedExam;
import pl.edu.prz.ai.exam.exams.domain.Question;
import pl.edu.prz.ai.exam.exams.domain.service.ExamsService;

import javax.validation.Valid;

@Controller
@RequestMapping("/exams")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamsController {
    ExamsService examsService;
    ObjectMapper objectMapper;

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<CreatedExam> createNewExam(@Valid @RequestBody CreateExam createExam) {
        return ResponseEntity.ok(examsService.createNewExam(createExam));
    }

    @PostMapping(value = "/{examId}/questions", consumes = {"multipart/form-data"})
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> addQuestionToExam(
            @PathVariable("examId") Long examId,
            @RequestParam("createQuestion") String createQuestion,
            @RequestParam("file") MultipartFile file) throws JsonProcessingException {
        CreateQuestion converted = objectMapper.readValue(createQuestion, CreateQuestion.class);
        Question question = examsService.addQuestionToExam(examId, converted);
        // TODO save file with given questionId
        examsService.addAttachmentToQuestion(question, file);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{examId}/groups")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> assignGroupToExam(
            @PathVariable("examId") Long examId,
            @Valid @RequestBody AssignGroup assignGroup){
        examsService.assignGroupToExam(examId, assignGroup);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{examId}/users")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> assignUserToExam(
            @PathVariable("examId") Long examId,
            @Valid @RequestBody AssignUser assignUser) {
        examsService.assignUserToExam(examId, assignUser);

        return ResponseEntity.ok().build();
    }
}

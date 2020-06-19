package pl.edu.prz.ai.exam.exams.application.rest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edu.prz.ai.exam.exams.application.request.AssignGroup;
import pl.edu.prz.ai.exam.exams.application.request.AssignUser;
import pl.edu.prz.ai.exam.exams.application.request.CreateExam;
import pl.edu.prz.ai.exam.exams.application.request.CreateQuestion;
import pl.edu.prz.ai.exam.exams.application.response.CreatedExam;
import pl.edu.prz.ai.exam.exams.domain.service.ExamsService;

import javax.validation.Valid;

@Controller
@RequestMapping("/exams")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamsController {
    ExamsService examsService;

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<CreatedExam> createNewExam(@Valid @RequestBody CreateExam createExam) {
        return ResponseEntity.ok(examsService.createNewExam(createExam));
    }

    @PostMapping("/{examId}/questions")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> addQuestionToExam(
            @PathVariable("examId") Long examId,
            @Valid @RequestBody CreateQuestion createQuestion) {
        examsService.addQuestionToExam(examId, createQuestion);

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

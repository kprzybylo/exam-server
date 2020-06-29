package pl.edu.prz.ai.exam.exams.application.rest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.edu.prz.ai.exam.exams.application.request.AssignGroup;
import pl.edu.prz.ai.exam.exams.application.request.AssignUser;
import pl.edu.prz.ai.exam.exams.application.request.ExamRequest;
import pl.edu.prz.ai.exam.exams.application.request.StartExam;
import pl.edu.prz.ai.exam.exams.application.response.ExamResponse;
import pl.edu.prz.ai.exam.exams.application.validation.ExistingExam;
import pl.edu.prz.ai.exam.exams.domain.service.ExamsService;

import javax.validation.Valid;

@Validated
@Controller
@RequestMapping("/exams")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamsController {
    ExamsService examsService;

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<ExamResponse> createNewExam(@Valid @RequestBody ExamRequest examRequest) {
        return ResponseEntity.ok(examsService.createNewExam(examRequest));
    }

    @PatchMapping("/{examId}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<ExamResponse> editExistingExam(
            @Valid @ExistingExam @PathVariable("examId") Long examId,
            @Valid @RequestBody ExamRequest examRequest
    ) {
        return ResponseEntity.ok(examsService.updateExistingExam(examId, examRequest));
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

    @PostMapping("/{examId}/start")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> startExam(
            @Valid @ExistingExam @PathVariable("examId") Long examId,
            @Valid @RequestBody StartExam startExam) {
        examsService.startExam(examId, startExam);

        return ResponseEntity.ok().build();
    }
}

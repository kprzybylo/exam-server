package pl.edu.prz.ai.exam.exams.domain.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import pl.edu.prz.ai.exam.exams.application.request.CreateExam;
import pl.edu.prz.ai.exam.exams.application.response.CreatedExam;
import pl.edu.prz.ai.exam.exams.domain.Exam;
import pl.edu.prz.ai.exam.exams.domain.User;
import pl.edu.prz.ai.exam.exams.domain.exception.CouldNotAcquireLoggedUserException;
import pl.edu.prz.ai.exam.exams.domain.repository.ExamRepository;
import pl.edu.prz.ai.exam.exams.domain.repository.ExamUserRepository;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DomainExamsService implements ExamsService {
    ExamRepository examRepository;
    ExamUserRepository examUserRepository;

    ExamsMapper examsMapper = new ExamsMapper();

    @Override
    public CreatedExam createNewExam(CreateExam createExam) {
        Exam exam = examsMapper.toExam(createExam);


        User creator = examUserRepository.findByEmail(getLoggedUserEmail())
                .orElseThrow(CouldNotAcquireLoggedUserException::new);

        exam = exam.toBuilder()
                .creator(creator)
                .build();

        examRepository.save(exam);

        return examsMapper.toCreatedExam(exam);
    }

    private String getLoggedUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return username;
    }
}

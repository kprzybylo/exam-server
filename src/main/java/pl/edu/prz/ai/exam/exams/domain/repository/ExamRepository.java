package pl.edu.prz.ai.exam.exams.domain.repository;

import org.springframework.data.jpa.repository.Query;
import pl.edu.prz.ai.exam.exams.domain.Exam;

import java.util.List;

public interface ExamRepository {
    Exam findById(Long id);

    void save(Exam exam);

    @Query("SELECT e FROM Exam e WHERE " +
            "e.isActive = true " +
            "AND e.validTo <= CURRENT_DATE")
    List<Exam> findAllExpiredExams();

    @Query("SELECT e FROM Exam e WHERE " +
            "e.isActive = false " +
            "AND e.validFrom <= CURRENT_DATE " +
            "AND e.validTo >= CURRENT_DATE")
    List<Exam> findAllNotStartedExams();
}

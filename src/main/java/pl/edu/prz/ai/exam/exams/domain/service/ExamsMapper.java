package pl.edu.prz.ai.exam.exams.domain.service;

import pl.edu.prz.ai.exam.exams.application.request.CreateExam;
import pl.edu.prz.ai.exam.exams.application.response.CreatedExam;
import pl.edu.prz.ai.exam.exams.domain.Exam;

public class ExamsMapper {
    public Exam toExam(CreateExam createExam) {
        return Exam.builder()
                .examName(createExam.getExamName())
                .availableTime(createExam.getAvailableTimeInMinutes())
                .validFrom(createExam.getAvailableFrom())
                .validTo(createExam.getAvailableTo())
                .build();
    }

    public CreatedExam toCreatedExam(Exam exam) {
        return CreatedExam.builder()
                .id(exam.getId())
                .examName(exam.getExamName())
                .availableFrom(exam.getValidFrom())
                .availableTo(exam.getValidTo())
                .availableTimeInMinutes(exam.getAvailableTime())
                .build();
    }
}

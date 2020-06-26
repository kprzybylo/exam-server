package pl.edu.prz.ai.exam.exams.domain.service;

import pl.edu.prz.ai.exam.exams.application.request.ExamRequest;
import pl.edu.prz.ai.exam.exams.application.response.ExamResponse;
import pl.edu.prz.ai.exam.exams.domain.Exam;

public class ExamsMapper {
    public Exam toExam(ExamRequest examRequest) {
        return Exam.builder()
                .examName(examRequest.getExamName())
                .availableTime(examRequest.getAvailableTimeInMinutes())
                .validFrom(examRequest.getAvailableFrom())
                .validTo(examRequest.getAvailableTo())
                .build();
    }

    public ExamResponse toCreatedExam(Exam exam) {
        return ExamResponse.builder()
                .id(exam.getId())
                .examName(exam.getExamName())
                .availableFrom(exam.getValidFrom())
                .availableTo(exam.getValidTo())
                .availableTimeInMinutes(exam.getAvailableTime())
                .build();
    }
}

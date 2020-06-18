package pl.edu.prz.ai.exam.exams.domain.service;

import pl.edu.prz.ai.exam.exams.application.request.CreateExam;
import pl.edu.prz.ai.exam.exams.application.response.CreatedExam;

public interface ExamsService {
    CreatedExam createNewExam(CreateExam createExam);
}
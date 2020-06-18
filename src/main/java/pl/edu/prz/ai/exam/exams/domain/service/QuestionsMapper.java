package pl.edu.prz.ai.exam.exams.domain.service;

import pl.edu.prz.ai.exam.exams.application.request.CreateQuestion;
import pl.edu.prz.ai.exam.exams.domain.Question;

public class QuestionsMapper {
    public Question toQuestion(CreateQuestion createQuestion) {
        return Question.builder()
                .question(createQuestion.getQuestion())
                .isOpenQuestion(createQuestion.getIsOpenQuestion())
                .build();
    }
}

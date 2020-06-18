package pl.edu.prz.ai.exam.exams.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table
public class Question {
    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "EXAM_ID", referencedColumnName = "ID")
    Exam exam;

    @NotNull
    String question;

    Boolean isOpenQuestion;
}

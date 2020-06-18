package pl.edu.prz.ai.exam.exams.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "EXAM_ID", referencedColumnName = "ID")
    Exam exam;

    @NotNull
    String question;

    Boolean isOpenQuestion;
}

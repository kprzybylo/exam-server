package pl.edu.prz.ai.exam.exams.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table
public class UsersAnswers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JoinColumn(name = "QUESTION_ID", referencedColumnName = "ID")
    Question question;

    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    User user;

    Timestamp answerTimestamp;

    String userAnswer;

    @JoinColumn(name = "ANSWER_ID", referencedColumnName = "ID")
    Answer answer;

    Float score;
}

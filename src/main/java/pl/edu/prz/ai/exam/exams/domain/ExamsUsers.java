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
public class ExamsUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    User user;

    @ManyToOne
    @JoinColumn(name = "EXAM_ID", referencedColumnName = "ID")
    Exam exam;

    Timestamp beginTimestamp;
    Timestamp endTimestamp;
    Float percentageScore;
}

package pl.edu.prz.ai.exam.exams.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "CREATOR_ID", referencedColumnName = "ID")
    User creator;

    String examName;

    Long availableTime;

    Date validFrom;
    Date validTo;
}

package pl.edu.prz.ai.exam.exams.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "EXAM_GROUP")
public class Group {
    @Id
    Long id;

    String groupName;

    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "id")
    User owner;

    @ManyToMany
    @JoinTable(
            name = "EXAM_GROUPS_USERS",
            joinColumns = @JoinColumn(name = "EXAM_GROUP_ID"),
            inverseJoinColumns = @JoinColumn(name = "APP_USER_ID")
    )
    Set<User> students;
}

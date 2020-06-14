package pl.edu.prz.ai.exam.users.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "USERS_ROLE")
@Table(name = "ROLE")
public class Role {
    @Id
    Long id;

    @Getter String roleName;
}

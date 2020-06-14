package pl.edu.prz.ai.exam.users.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "USERS_USER")
@Table(name = "APP_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull @NotEmpty
    String firstName;
    @NotNull @NotEmpty
    String lastName;

    @NotNull @NotEmpty
    String password;

    @NotNull @NotEmpty @Email
    String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    Role role;

    Boolean isNonLocked;
    Boolean isEnabled;
}

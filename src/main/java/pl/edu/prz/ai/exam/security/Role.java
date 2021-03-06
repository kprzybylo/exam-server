package pl.edu.prz.ai.exam.security;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "SECURITY_ROLE")
@Table(name = "ROLE")
@FieldDefaults(level = AccessLevel.PRIVATE)
class Role implements GrantedAuthority {
    @Id
    Long id;

    String roleName;

    @Override
    public String getAuthority() {
        return "ROLE_" + roleName;
    }
}

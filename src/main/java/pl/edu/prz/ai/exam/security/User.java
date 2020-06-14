package pl.edu.prz.ai.exam.security;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Entity(name = "SECURITY_USER")
@Table(name = "APP_USER")
@FieldDefaults(level = AccessLevel.PRIVATE)
class User implements UserDetails {
    @Id
    @GeneratedValue
    Long id;

    @Getter String password;

    String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    Role role;

    Date expirationDate;
    Date passwordExpirationDate;
    Boolean isNonLocked;
    Boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return expirationDate.after(new Date());
    }

    @Override
    public boolean isAccountNonLocked() {
        return isNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return passwordExpirationDate.after(new Date());
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}

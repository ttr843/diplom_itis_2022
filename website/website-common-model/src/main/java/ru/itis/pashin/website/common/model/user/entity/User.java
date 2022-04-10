package ru.itis.pashin.website.common.model.user.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;


/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(schema = "users", name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String middleName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private UUID guid;

    @NotEmpty
    private String phoneNumber;

    private Integer age;

    private String country;

    private String city;

    private String street;

    private Integer house;

    @NotEmpty
    private String email;

    @NotEmpty
    private String passwordEncrypted;

    private boolean isBlocked;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.getCode()));
    }

    @Override
    public String getPassword() {
        return this.passwordEncrypted;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.isBlocked;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.isBlocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.isBlocked;
    }

    @Override
    public boolean isEnabled() {
        return !this.isBlocked;
    }
}

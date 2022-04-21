package ru.itis.pashin.website.common.model.user.entity;

import lombok.*;
import ru.itis.pashin.website.common.model.catalogs.entity.Bank;
import ru.itis.pashin.website.common.model.user.util.UserGuidGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners({UserGuidGenerator.class})
@Table(schema = "users", name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Не должно быть пустым")
    private String firstName;

    @NotEmpty(message = "Не должно быть пустым")
    private String middleName;

    @NotEmpty(message = "Не должно быть пустым")
    private String lastName;

    @NotNull
    private UUID guid;

    @NotEmpty(message = "Не должно быть пустым")
    private String phoneNumber;

    private Short age;

    private String country;

    private String city;

    private String street;

    private Short house;

    @NotEmpty(message = "Не должно быть пустым")
    private String email;

    @NotNull
    private String passwordEncrypted;

    private boolean isBlocked;

    private boolean isConfirmed;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Bank bank;

    private String position;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Role role;
}

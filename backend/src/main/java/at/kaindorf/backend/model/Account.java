package at.kaindorf.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

/**
 * The Account class is used to save an Account for the Webinterface in the Database
 * @author <b> Berger S., Großschedl S., Riedner N.</b>
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private LocalDate birthdate;
    private String avatar;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}

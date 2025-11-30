package at.kaindorf.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Person class is used to be a Superclass for Trainer and Trainee.
 * @author <b>Berger S., Großschedl S., Riedner N.</b>
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type",
        discriminatorType = DiscriminatorType.STRING)
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstname;
    private String lastname;
    @Enumerated(EnumType.STRING)
    private Rank rank;
}

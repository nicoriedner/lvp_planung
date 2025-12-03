package at.kaindorf.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * The Trainer class is used to save a Trainer and his competences to the database.
 * Additionally, some details regarding his working model are saved within.
 * It is a subclass of the abstract Person class.
 * @author <b>Berger S., Großschedl S., Riedner N.</b>
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("Trainer")
public class Trainer extends Person {
    private int maximumWorkHours;
    @Enumerated(EnumType.STRING)
    private Workmodel workmodel;
    @ManyToMany
    @JoinTable(name = "trainer_competence",
            // TODO: Check if you need PersonID here
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "competence_id")
    )
    private List<Competence> competences;
    @OneToMany(mappedBy = "trainer")
    private List<ConcreteCourse> courses;
}

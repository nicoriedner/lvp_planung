package at.kaindorf.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * The Trainee class is used to save a Trainee of a course to the database.
 * It is a subclass of the abstract Person class.
 * @author <b>Berger S., Großschedl S., Riedner N.</b>
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("Trainee")
public class Trainee extends Person {
    @ManyToMany
    @JoinTable(name = "trainee_concrete_course",
            joinColumns = @JoinColumn(name = "trainee_id"),
            inverseJoinColumns = @JoinColumn(name = "concrete_course_id")
    )
    private List<ConcreteCourse> courses;
}

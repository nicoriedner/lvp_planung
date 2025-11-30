package at.kaindorf.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * The Classroom class is used to save an Entity of a Classroom to the Database
 * @author <b>Berger S., Großschedl S., Riedner N.</b>
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String additionalInformation;
    private int numberOfSeats;

    @OneToMany(mappedBy = "classroom")
    private List<ConcreteCourse> courses;
}

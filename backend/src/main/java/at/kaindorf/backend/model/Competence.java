package at.kaindorf.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * The Competence class is used to save predefined competences of the trainers to the database
 * @author <b>Berger S., Großschedl S., Riedner N.</b>
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String description;
    /**
     * Inverse connection to requiredCompetences in Course class
     */
    @ManyToMany(mappedBy = "requiredCompetences")
    private Set<Course> courses;
    @ManyToMany(mappedBy = "competences")
    private Set<Trainer> trainers;
}

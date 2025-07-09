package at.kaindorf.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    private Double stunden;

    @Enumerated(EnumType.STRING)
    private Dienstgrad dienstgrad;

    @Enumerated(EnumType.STRING)
    private Wochenmodell wochenmodell;

    @ManyToMany
    private List<Kompetenz> kompetenzen;

    @ManyToMany
    private List<Schulungstermin> schulungstermine;
}

package at.kaindorf.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Schulungstermin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDatum;
    private LocalDate endDatum;

    private int anzTeilnehmer;

    private Status status;

    @ManyToMany
    private List<Ressource> ressource;

    @ManyToMany
    private List<Person> teilnehmer;

    @ManyToOne
    private Lehrsaal lehrsaal;

    @ManyToOne
    private Lehrgang lehrgang;
}

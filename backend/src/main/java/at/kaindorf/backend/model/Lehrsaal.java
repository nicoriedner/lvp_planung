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
public class Lehrsaal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bezeichnung;

    private String beschreibung;

    private int sitzPlaetze;

    private String ausstattung;

    @OneToMany(mappedBy = "lehrsaal")
    private List<Schulungstermin> schulungstermine;
}

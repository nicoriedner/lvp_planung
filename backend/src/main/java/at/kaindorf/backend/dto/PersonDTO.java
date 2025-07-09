package at.kaindorf.backend.dto;

import at.kaindorf.backend.model.Dienstgrad;
import at.kaindorf.backend.model.Kompetenz;
import at.kaindorf.backend.model.Schulungstermin;
import at.kaindorf.backend.model.Wochenmodell;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private String firstName;
    private String lastName;
    private Dienstgrad dienstgrad;
    private Double stunden;
    private Wochenmodell wochenmodell;
    private List<Kompetenz> kompetenzen;
    private List<Schulungstermin> schulungstermine;
}

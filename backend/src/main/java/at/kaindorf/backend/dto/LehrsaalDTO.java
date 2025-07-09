package at.kaindorf.backend.dto;

import at.kaindorf.backend.model.Schulungstermin;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LehrsaalDTO {
    private String bezeichnung;
    private String beschreibung;
    private int sitzPlaetze;
    private String ausstattung;
    private List<Schulungstermin> schulungstermine;
}

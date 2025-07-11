package at.kaindorf.backend.dto;

import at.kaindorf.backend.model.Kompetenz;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LehrgangDTO {
    private String bezeichnung;
    private String beschreibung;
    private int dauer;
    private List<Kompetenz> kompetenzen;
}

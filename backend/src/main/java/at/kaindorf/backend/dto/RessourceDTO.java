package at.kaindorf.backend.dto;

import at.kaindorf.backend.model.RessourcenTyp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RessourceDTO {
    private String bezeichnung;
    private RessourcenTyp typ;
    private boolean isAvailable;
    private int inventarNr;
}

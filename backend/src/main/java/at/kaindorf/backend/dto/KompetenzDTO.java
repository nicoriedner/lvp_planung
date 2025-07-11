package at.kaindorf.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KompetenzDTO {
    private String bezeichnung;

    private String beschreibung;
}

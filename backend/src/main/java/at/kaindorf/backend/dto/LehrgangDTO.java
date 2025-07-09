package at.kaindorf.backend.dto;

import at.kaindorf.backend.model.Kompetenz;
import jakarta.persistence.ManyToMany;

import java.util.List;

public class LehrgangDTO {
    private String bezeichnung;
    private String beschreibung;
    private int dauer;
    private List<Kompetenz> kompetenzen;
}

package at.kaindorf.backend.dto;

import at.kaindorf.backend.model.*;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchulungsterminDTO {

    private LocalDate startDatum;
    private LocalDate endDatum;
    private int anzTeilnehmer;
    private Status status;
    private List<Ressource> ressource;
    private List<Person> teilnehmer;
    private Lehrsaal lehrsaal;
    private Lehrgang lehrgang;
}

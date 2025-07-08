package at.kaindorf.backend.dto;

import at.kaindorf.backend.model.*;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.List;

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

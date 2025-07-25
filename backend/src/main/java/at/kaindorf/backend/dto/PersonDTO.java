package at.kaindorf.backend.dto;

import at.kaindorf.backend.model.Rank;
import at.kaindorf.backend.model.Competence;
import at.kaindorf.backend.model.Trainingdate;
import at.kaindorf.backend.model.Weekmodel;
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
    private Rank rank;
    private Double hours;
    private Weekmodel weekmodel;
    private List<Competence> competences;
    private List<Trainingdate> trainingdates;
}

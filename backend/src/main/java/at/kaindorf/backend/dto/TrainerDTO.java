package at.kaindorf.backend.dto;

import at.kaindorf.backend.model.Competence;
import at.kaindorf.backend.model.ConcreteCourse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDTO {
    private String firstname;
    private String lastname;
    private String rank;
    private int maximumWorkHours;
    private List<Competence> competences;
    private List<ConcreteCourse> courses;
}

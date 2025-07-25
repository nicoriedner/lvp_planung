package at.kaindorf.backend.dto;

import at.kaindorf.backend.model.Competence;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private String name;
    private String description;
    private int duration;
    private List<Competence> competences;
}

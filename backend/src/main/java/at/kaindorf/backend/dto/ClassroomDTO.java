package at.kaindorf.backend.dto;

import at.kaindorf.backend.model.Trainingdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomDTO {
    private String name;
    private String description;
    private int seating;
    private String equipment;
    private List<Trainingdate> trainingdates;
}

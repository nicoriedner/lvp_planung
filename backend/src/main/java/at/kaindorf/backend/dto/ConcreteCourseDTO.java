package at.kaindorf.backend.dto;

import at.kaindorf.backend.model.Classroom;
import at.kaindorf.backend.model.Trainee;
import at.kaindorf.backend.model.Trainer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConcreteCourseDTO {
    private String name;
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;
    private List<Trainee> trainees;
    private Trainer trainer;
    private Classroom classroom;
}

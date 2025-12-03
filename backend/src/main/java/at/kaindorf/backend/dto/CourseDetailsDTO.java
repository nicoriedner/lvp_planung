package at.kaindorf.backend.dto;

import at.kaindorf.backend.model.Classroom;
import at.kaindorf.backend.model.Resource;
import at.kaindorf.backend.model.Trainer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetailsDTO {
    private Trainer trainer;
    private Classroom classroom;
    private List<Resource> resources;
}

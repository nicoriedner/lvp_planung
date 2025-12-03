package at.kaindorf.backend.dto;

import at.kaindorf.backend.model.Course;
import at.kaindorf.backend.model.Rank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraineeDTO {
    private String firstname;
    private String lastname;
    private Rank rank;
    private List<Course> courses;
}
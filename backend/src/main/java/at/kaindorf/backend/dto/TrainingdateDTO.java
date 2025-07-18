package at.kaindorf.backend.dto;

import at.kaindorf.backend.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingdateDTO {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int numParticipants;
    private Status status;
    private List<Resource> resource;
    private List<Person> participants;
    private Classroom classroom;
    private Course course;
}

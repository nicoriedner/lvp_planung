package at.kaindorf.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Trainingdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private int numParticipants;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany
    private List<Resource> resource;

    @ManyToMany
    private List<Person> participants;

    @ManyToOne
    private Classroom classroom;

    @ManyToOne
    private Course course;

    @OneToOne
    private Person teacher;
}

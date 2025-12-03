package at.kaindorf.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeeklyCoursesDTO {
    private long id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}

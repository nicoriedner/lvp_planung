package at.kaindorf.backend.repositories;

import at.kaindorf.backend.dto.ConcreteCourseDetailsDTO;
import at.kaindorf.backend.dto.WeeklyCoursesDTO;
import at.kaindorf.backend.model.Classroom;
import at.kaindorf.backend.model.ConcreteCourse;
import at.kaindorf.backend.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ConcreteCourseRepository extends JpaRepository<ConcreteCourse, Long> {
    @Query("SELECT c FROM ConcreteCourse c WHERE c.classroom = ?1 AND c.startTime = ?2 AND c.endTime = ?3")
    ConcreteCourse findConcreteCourseByClassroomAndStartDateAndEndDate(Classroom classroom, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT c FROM ConcreteCourse c WHERE c.classroom = ?1")
    List<ConcreteCourse> findConcreteCourseByClassroom(Classroom classroom);

    @Query("SELECT c FROM ConcreteCourse c WHERE c.trainer = ?1")
    List<ConcreteCourse> findConcreteCourseByTrainer(Trainer trainer);

    // Unbedingt mit Daten Testen; Funktionen in Postgres console getestet
    @Query("""
           SELECT new at.kaindorf.backend.dto.WeeklyCoursesDTO(c.id, c.name, c.startTime, c.endTime)
           FROM ConcreteCourse c
           WHERE FUNCTION('date_part', 'week', c.startTime) = :weekOfYear AND FUNCTION('date_part', 'year', c.startTime) = :year
    """)
    List<WeeklyCoursesDTO> getConcreteCourseForWeekKey(int year, int weekOfYear);

    @Query("SELECT new at.kaindorf.backend.dto.ConcreteCourseDetailsDTO(c.trainer, c.classroom, c.resources) FROM ConcreteCourse c WHERE c.id = :id")
    ConcreteCourseDetailsDTO getConcreteCourseDetails(long id);
}

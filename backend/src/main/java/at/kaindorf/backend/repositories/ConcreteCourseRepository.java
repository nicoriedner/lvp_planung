package at.kaindorf.backend.repositories;

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
}

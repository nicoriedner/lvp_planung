package at.kaindorf.backend.repositories;

import at.kaindorf.backend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TrainingdateRepository extends JpaRepository<Trainingdate, Long> {

    @Query("SELECT s FROM Trainingdate s WHERE s.numParticipants > ?1")
    List<Trainingdate> findTrainingdateByAnzParticipantsGreaterThan(Integer anzParticipants);

    @Query("SELECT s FROM Trainingdate s WHERE s.numParticipants < ?1")
    List<Trainingdate> findTrainingdateByAnzParticipantsSmallerThan(Integer anzParticipants);

    @Query("SELECT s FROM Trainingdate s where s.numParticipants = ?1")
    List<Trainingdate> findTrainingdateByParticipants(Integer participants);

    List<Trainingdate> findTrainingdateByStatus(Status status);

    @Query("SELECT s FROM Trainingdate s WHERE s.startDate > ?1")
    List<Trainingdate> findTrainingdateByStartDateAfter(LocalDateTime startDate);

    @Query("SELECT s FROM Trainingdate s WHERE s.startDate < ?1")
    List<Trainingdate> findTrainingdateByStartDateBefore(LocalDateTime startDate);

    List<Trainingdate> findTrainingdateByStartDate(LocalDateTime startDate);

    List<Trainingdate> findTrainingdateByEndDateAfter(LocalDateTime endDate);

    List<Trainingdate> findTrainingdateByEndDateBefore(LocalDateTime endDate);

    List<Trainingdate> findTrainingdateByEndDate(LocalDateTime endDate);

    @Query("SELECT s FROM Trainingdate s WHERE s.classroom = ?1 AND s.startDate = ?2 AND s.endDate = ?3")
    Trainingdate findTrainingdateByClassroomAndStartDateAndEndDate(Classroom classroom, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT s FROM Trainingdate s WHERE s.classroom = ?1")
    List<Trainingdate> findTrainingdateByClassroom(Classroom classroom);

    @Query("SELECT s FROM Trainingdate s WHERE s.course = ?1")
    List<Trainingdate> findTrainingdateByCourse(Course course);

    @Query("SELECT s FROM Trainingdate s WHERE s.teacher = ?1")
    List<Trainingdate> findTrainingdateByTeacher(Person teacher);
}

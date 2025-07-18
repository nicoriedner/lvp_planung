package at.kaindorf.backend.repositories;

import at.kaindorf.backend.model.Classroom;
import at.kaindorf.backend.model.Trainingdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    @Query("SELECT l FROM Classroom l WHERE l.id = ?1")
    Classroom findClassroomById(Long id);

    @Query("SELECT l FROM Classroom l WHERE l.equipment = ?1")
    List<Classroom> findClassroomByEquipment(String equipment);

    @Query("SELECT l FROM Classroom l WHERE l.seating >= ?1")
    List<Classroom> findClassroomBySeatingGreaterThanEqual(Integer seating);

    @Query("SELECT l FROM Classroom l WHERE l.name = ?1")
    Classroom findClassroomByName(String name);

    @Query("SELECT l FROM Classroom l WHERE :schulungstermin MEMBER OF l.trainingdates")
    List<Classroom> findClassroomByTrainingdate(Trainingdate trainingdate);
}
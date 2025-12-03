package at.kaindorf.backend.repositories;

import at.kaindorf.backend.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    @Query("SELECT c FROM Classroom c WHERE c.id = ?1")
    Classroom findClassroomById(Long id);

    @Query("SELECT c FROM Classroom c WHERE c.numberOfSeats >= ?1")
    List<Classroom> findClassroomByMinimumAmountOfSeats(Integer numberOfSeats);

    @Query("SELECT c FROM Classroom c WHERE c.name = ?1")
    Classroom findClassroomByName(String name);
}
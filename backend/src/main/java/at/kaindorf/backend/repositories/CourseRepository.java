package at.kaindorf.backend.repositories;

import at.kaindorf.backend.dto.CourseDetailsDTO;
import at.kaindorf.backend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.name = ?1")
    Course findCourseByName(String name);

    @Query("SELECT c FROM Course c where c.durationInDays > ?1")
    Course findCourseByDurationGreaterThan(Integer duration);

    @Query("SELECT c FROM Course c where c.durationInDays < ?1")
    Course findCourseByDurationLessThan(Integer duration);

    @Query("SELECT c FROM Course c where c.durationInDays = ?1")
    List<Course> findCourseByDuration(Integer duration);

    // TODO: Finishing the Query in this Repo or in the ConcreteCourse Repo
    // @Query("SELECT new at.kaindorf.backend.dto.CourseDetailsDTO(c.trainer, c.classroom) FROM Course c ")
    //CourseDetailsDTO findCourseDetailsById(Long id);
}

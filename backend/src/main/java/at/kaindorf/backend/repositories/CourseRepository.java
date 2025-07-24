package at.kaindorf.backend.repositories;

import at.kaindorf.backend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT l FROM Course l WHERE l.name = ?1")
    Course findCourseByName(String name);

    @Query("SELECT l FROM Course l where l.duration > ?1")
    Course findCourseByDurationGreaterThan(Integer duration);

    @Query("SELECT l FROM Course l where l.duration < ?1")
    Course findCourseByDurationLessThan(Integer duration);

    @Query("SELECT l FROM Course l where l.duration = ?1")
    List<Course> findCourseByDuration(Integer duration);
}

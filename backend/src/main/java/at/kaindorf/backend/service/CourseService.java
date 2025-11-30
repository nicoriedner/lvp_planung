package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.CourseDTO;
import at.kaindorf.backend.exceptions.CourseNotFoundException;
import at.kaindorf.backend.mapper.CourseMapper;
import at.kaindorf.backend.model.Course;
import at.kaindorf.backend.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public List<CourseDTO> findAll() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(courseMapper::toDTO).toList();
    }

    public CourseDTO findCourseByName(String name) {
        return courseMapper.toDTO(courseRepository.findCourseByName(name));
    }

    public List<CourseDTO> findCourseByDuration(Integer duration ) {
        return courseRepository.findCourseByDuration(duration)
                .stream()
                .map(courseMapper::toDTO)
                .toList();
    }

    public CourseDTO findCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        return courseMapper.toDTO(course);
    }

    public Long createNewCourse(CourseDTO courseDTO) {
        Course course = courseMapper.toEntity(courseDTO);
        return courseRepository.save(course).getId();
    }

    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        courseRepository.delete(course);
    }

    public void updateCourse(Long id, CourseDTO courseDTO) {
        if(courseRepository.existsById(id)) {
            Course course = courseRepository.findById(id).get();
            Course newCourse = courseMapper.toEntity(courseDTO);
            newCourse.setId(course.getId());
            courseRepository.save(newCourse);
        } else {
            throw new CourseNotFoundException(id);
        }
    }
}

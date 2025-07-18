package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.CourseDTO;
import at.kaindorf.backend.exceptions.CourseNotFoundException;
import at.kaindorf.backend.mapper.CourseMapper;
import at.kaindorf.backend.model.Course;
import at.kaindorf.backend.repositories.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
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

    public CourseDTO findById(Long id) {
        if(!courseRepository.findById(id).isPresent()) {
            throw new CourseNotFoundException(id);
        }
        return courseMapper.toDTO(courseRepository.findById(id).get());
    }

    public Long createNewCourse(CourseDTO courseDTO) {
        Course course = courseMapper.toEntity(courseDTO);
        return courseRepository.save(course).getId();
    }

    public void deleteCourse(Long id) {
        if(courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else {
            throw new CourseNotFoundException(id);
        }
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

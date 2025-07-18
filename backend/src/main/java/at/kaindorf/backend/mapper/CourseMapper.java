package at.kaindorf.backend.mapper;

import at.kaindorf.backend.dto.CourseDTO;
import at.kaindorf.backend.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDTO toDTO(Course course);
    Course toEntity(CourseDTO courseDTO);
}

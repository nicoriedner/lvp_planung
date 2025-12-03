package at.kaindorf.backend.mapper;

import at.kaindorf.backend.dto.ConcreteCourseDTO;
import at.kaindorf.backend.model.ConcreteCourse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConcreteCourseMapper {
    ConcreteCourseDTO toDTO(ConcreteCourse concreteCourse);
    ConcreteCourse toEntity(ConcreteCourseDTO concreteCourseDTO);
}
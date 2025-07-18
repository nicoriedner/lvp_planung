package at.kaindorf.backend.mapper;

import at.kaindorf.backend.dto.ClassroomDTO;
import at.kaindorf.backend.model.Classroom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {
    ClassroomDTO toDTO(Classroom classroom);
    Classroom toEntity(ClassroomDTO classroomDTO);
}

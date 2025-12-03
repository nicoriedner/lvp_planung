package at.kaindorf.backend.mapper;


import at.kaindorf.backend.dto.TraineeDTO;
import at.kaindorf.backend.model.Trainee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TraineeMapper {
    TraineeDTO toDTO(Trainee trainee);
    Trainee toEntity(TraineeDTO resourceDTO);
}

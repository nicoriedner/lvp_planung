package at.kaindorf.backend.mapper;

import at.kaindorf.backend.dto.TrainingdateDTO;
import at.kaindorf.backend.model.Trainingdate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrainingdateMapper {
    TrainingdateDTO toDTO(Trainingdate trainingdate);
    Trainingdate toEntity(TrainingdateDTO trainingdateDTO);
}

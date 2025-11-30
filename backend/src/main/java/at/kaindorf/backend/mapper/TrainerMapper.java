package at.kaindorf.backend.mapper;

import at.kaindorf.backend.dto.TrainerDTO;
import at.kaindorf.backend.model.Trainer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrainerMapper {
    TrainerDTO toDTO(Trainer trainer);
    Trainer toEntity(TrainerDTO trainerDTO);
}

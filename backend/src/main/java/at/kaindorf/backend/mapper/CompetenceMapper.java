package at.kaindorf.backend.mapper;

import at.kaindorf.backend.dto.CompetenceDTO;
import at.kaindorf.backend.model.Competence;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompetenceMapper {
    CompetenceDTO toDTO(Competence competence);
    Competence toEntity(CompetenceDTO competenceDTO);
}

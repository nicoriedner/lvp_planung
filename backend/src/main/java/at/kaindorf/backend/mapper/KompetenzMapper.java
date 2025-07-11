package at.kaindorf.backend.mapper;

import at.kaindorf.backend.dto.KompetenzDTO;
import at.kaindorf.backend.model.Kompetenz;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KompetenzMapper {
    KompetenzDTO toDTO(Kompetenz kompetenz);
    Kompetenz toEntity(KompetenzDTO kompetenzDTO);
}

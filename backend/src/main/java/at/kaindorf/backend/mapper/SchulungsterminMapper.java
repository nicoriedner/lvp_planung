package at.kaindorf.backend.mapper;

import at.kaindorf.backend.dto.SchulungsterminDTO;
import at.kaindorf.backend.model.Schulungstermin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchulungsterminMapper {
    SchulungsterminDTO toDTO(Schulungstermin schulungstermin);
    Schulungstermin toEntity(SchulungsterminDTO schulungsterminDTO);
}

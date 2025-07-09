package at.kaindorf.backend.mapper;

import at.kaindorf.backend.dto.LehrgangDTO;
import at.kaindorf.backend.model.Lehrgang;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LehrgangMapper {
    LehrgangDTO toDTO(Lehrgang lehrgang);
    Lehrgang toEntity(LehrgangDTO lehrgangDTO);
}

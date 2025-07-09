package at.kaindorf.backend.mapper;

import at.kaindorf.backend.dto.LehrsaalDTO;
import at.kaindorf.backend.model.Lehrsaal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LehrsaalMapper {
    public LehrsaalDTO toDTO(Lehrsaal lehrsaal);
    public Lehrsaal toEntity(LehrsaalDTO lehrsaalDTO);
}

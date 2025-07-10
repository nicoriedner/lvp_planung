package at.kaindorf.backend.mapper;

import at.kaindorf.backend.dto.RessourceDTO;
import at.kaindorf.backend.model.Ressource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RessourceMapper {
    RessourceDTO toDTO(Ressource ressource);
    Ressource toEntity(RessourceDTO ressourceDTO);
}

package at.kaindorf.backend.mapper;

import at.kaindorf.backend.dto.ResourceDTO;
import at.kaindorf.backend.model.Resource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResourceMapper {
    ResourceDTO toDTO(Resource resource);
    Resource toEntity(ResourceDTO resourceDTO);
}

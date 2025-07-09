package at.kaindorf.backend.mapper;

import at.kaindorf.backend.dto.PersonDTO;
import at.kaindorf.backend.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDTO toDTO(Person person);
    Person toEntity(PersonDTO personDTO);
}

package at.kaindorf.backend.mapper;

import at.kaindorf.backend.dto.PersonDTO;
import at.kaindorf.backend.model.Person;

public interface PersonMapper {
    PersonDTO toDTO(Person person);

    Person toPerson(PersonDTO personDTO);
}

package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.PersonDTO;
import at.kaindorf.backend.exceptions.PersonNotFoundException;
import at.kaindorf.backend.mapper.PersonMapper;
import at.kaindorf.backend.model.Kompetenz;
import at.kaindorf.backend.model.Person;
import at.kaindorf.backend.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public List<PersonDTO> findAll() {
        List<Person> persons = personRepository.findAll();
        return persons.stream()
                .map(personMapper::toDTO)
                .toList();
    }

    public PersonDTO findById(Long id) {
        if(!personRepository.findById(id).isPresent()) {
            throw new PersonNotFoundException(id);
        }
        return personMapper.toDTO(personRepository.findById(id).get());
    }

    public List<PersonDTO> findByKompetenzen(List<Kompetenz> kompetenzen) {
        List<Person> persons = personRepository.findPersonsByKompetenzen(kompetenzen);
        return persons.stream()
                .map(personMapper::toDTO)
                .toList();
    }

    public List<PersonDTO> findByLastName(String lastname) {
        List<Person> persons = personRepository.findPersonByLastName(lastname);
        return persons.stream()
                .map(personMapper::toDTO)
                .toList();
    }

    public Long createNewPerson(PersonDTO personDTO) {
        Person person = personMapper.toEntity(personDTO);
        return personRepository.save(person).getId();
    }

    public void deletePerson(Long id) {
        if(personRepository.findPersonById(id) == null) {
            throw new PersonNotFoundException(id);
        }
        personRepository.deleteById(id);
    }

    public void updatePerson(Long id, PersonDTO personDTO) {
        Person newPerson = personMapper.toEntity(personDTO);
        newPerson.setId(id);
        personRepository.save(newPerson);
    }
}

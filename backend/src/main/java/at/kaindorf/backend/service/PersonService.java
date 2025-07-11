package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.PersonDTO;
import at.kaindorf.backend.mapper.KompetenzMapper;
import at.kaindorf.backend.mapper.PersonMapper;
import at.kaindorf.backend.model.Kompetenz;
import at.kaindorf.backend.model.Person;
import at.kaindorf.backend.repositories.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        Person person = personRepository.findById(id).orElse(null);
        return personMapper.toDTO(person);
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
            throw new EntityNotFoundException("Person mit ID " + id + " existiert nicht");
        }
        personRepository.deleteById(id);
    }

    public void updatePerson(Long id, PersonDTO personDTO) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person mit ID " + id + " existiert nicht!"));

        Person updatedPerson = personMapper.toEntity(personDTO);
        updatedPerson.setId(id);
        person.setFirstName(updatedPerson.getFirstName());
        person.setLastName(updatedPerson.getLastName());
        person.setStunden(updatedPerson.getStunden());
        person.setWochenmodell(updatedPerson.getWochenmodell());
        person.setDienstgrad(updatedPerson.getDienstgrad());
        person.setSchulungstermine(updatedPerson.getSchulungstermine());
        personRepository.save(person);
    }
}

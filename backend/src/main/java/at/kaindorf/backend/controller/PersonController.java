package at.kaindorf.backend.controller;

import at.kaindorf.backend.dto.PersonDTO;
import at.kaindorf.backend.model.Kompetenz;
import at.kaindorf.backend.model.Person;
import at.kaindorf.backend.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPersonById(
            @PathVariable long id
    ) {
        return ResponseEntity.ok(personService.findById(id));
    }

    @GetMapping("/nachname/{lastname}")
    public ResponseEntity<List<PersonDTO>> getPersonByLastname(
            @PathVariable String lastname
    ){
        return ResponseEntity.ok(personService.findByLastName(lastname));
    }

    @GetMapping("/kompetenz/{kompetenzen}")
    public ResponseEntity<List<PersonDTO>> getPersonByKompetenzen(
            @PathVariable List<Kompetenz> kompetenzen
    ){
        return ResponseEntity.ok(personService.findByKompetenzen(kompetenzen));
    }

    @PostMapping("/new")
    public ResponseEntity<Long> createPerson(
            @RequestBody PersonDTO personDTO
    ){
        return ResponseEntity.ok(personService.createNewPerson(personDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void deletePerson(
            @PathVariable long id
    ){
        personService.deletePerson(id);
    }

    @PutMapping("/update/{id}")
    public void updatePerson(
            @PathVariable Long id,
            @RequestBody PersonDTO personDTO
    ){
        personService.updatePerson(id, personDTO);
        ResponseEntity.ok();
    }
}

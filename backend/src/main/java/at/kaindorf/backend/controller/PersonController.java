package at.kaindorf.backend.controller;

import at.kaindorf.backend.dto.PersonDTO;
import at.kaindorf.backend.model.Kompetenz;
import at.kaindorf.backend.model.Person;
import at.kaindorf.backend.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{lastname}")
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

}

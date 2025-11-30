package at.kaindorf.backend.controller;

import at.kaindorf.backend.dto.TrainerDTO;
import at.kaindorf.backend.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainer")
public class TrainerController {
    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<TrainerDTO>> getAllTrainer() {
        return ResponseEntity.ok(personService.findAllTrainer());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerDTO> getTraineeById(
            @PathVariable long id
    ){
        return ResponseEntity.ok(personService.findTrainerById(id));
    }

    @GetMapping("/lastname/{lastname}")
    public ResponseEntity<List<TrainerDTO>> getTrainerByLastname(
            @PathVariable String lastname
    ){
        return ResponseEntity.ok(personService.findTrainerByLastname(lastname));
    }

    @PostMapping("/new")
    public ResponseEntity<Long> createTrainer(
            @RequestBody TrainerDTO trainerDTO
    ){
        return ResponseEntity.ok(personService.createNewTrainer(trainerDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTrainer(
            @PathVariable long id
    ){
        personService.deleteTrainer(id);
    }

    @PutMapping("/update/{id}")
    public void updateTrainee(
            @PathVariable Long id,
            @RequestBody TrainerDTO trainerDTO
    ){
        personService.updateTrainer(id, trainerDTO);
        ResponseEntity.ok();
    }
}

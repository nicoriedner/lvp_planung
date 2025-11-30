package at.kaindorf.backend.controller;

import at.kaindorf.backend.dto.TraineeDTO;
import at.kaindorf.backend.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainee")
public class TraineeController {
    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<TraineeDTO>> getAllTrainees() {
        return ResponseEntity.ok(personService.findAllTrainees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TraineeDTO> getTraineeById(
            @PathVariable long id
    ){
        return ResponseEntity.ok(personService.findTraineeById(id));
    }

    @GetMapping("/lastname/{lastname}")
    public ResponseEntity<List<TraineeDTO>> getTraineeByLastname(
            @PathVariable String lastname
    ){
        return ResponseEntity.ok(personService.findTraineeByLastname(lastname));
    }

    @PostMapping("/new")
    public ResponseEntity<Long> createTrainee(
            @RequestBody TraineeDTO traineeDTO
    ){
        return ResponseEntity.ok(personService.createNewTrainee(traineeDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTrainee(
            @PathVariable long id
    ){
        personService.deleteTrainee(id);
    }

    @PutMapping("/update/{id}")
    public void updateTrainee(
            @PathVariable Long id,
            @RequestBody TraineeDTO traineeDTO
    ){
        personService.updateTrainee(id, traineeDTO);
        ResponseEntity.ok();
    }
}

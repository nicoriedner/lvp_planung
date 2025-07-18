package at.kaindorf.backend.controller;

import at.kaindorf.backend.dto.TrainingdateDTO;
import at.kaindorf.backend.model.Course;
import at.kaindorf.backend.model.Classroom;
import at.kaindorf.backend.model.Person;
import at.kaindorf.backend.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/training")
public class TrainingdateController {
    private final TrainingService trainingService;

    @GetMapping
    public ResponseEntity<List<TrainingdateDTO>> getAllTrainingdate() {
        return ResponseEntity.ok(trainingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingdateDTO> getTrainingdate(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(trainingService.findById(id));
    }

    @GetMapping("/teacher/{teacher}")
    public ResponseEntity<List<TrainingdateDTO>> getAllTrainingdateByTeacher(
            @PathVariable Person teacher
            ){
        return ResponseEntity.ok(trainingService.findAllByTeacher(teacher));
    }

    @GetMapping("/allByParticipants/{participant}")
    public ResponseEntity<List<TrainingdateDTO>> getAllByParticipant(
            @PathVariable Person participant
    ){
     return ResponseEntity.ok(trainingService.findAllByParticipant(participant));
    }

    @GetMapping("/allByCourse/{course}")
    public ResponseEntity<List<TrainingdateDTO>> getAllByCourse(
            @PathVariable Course course
    ){
        return ResponseEntity.ok(trainingService.findAllByCourse(course));
    }

    @GetMapping("/allByClassroom/{classroom}")
    public ResponseEntity<List<TrainingdateDTO>> getAllByClassroom(
            @PathVariable Classroom classroom
            ){
        return ResponseEntity.ok(trainingService.findAllByClassroom(classroom));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTrainingdate(
            @PathVariable Long id
    ){
        trainingService.deleteTrainingdate(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createTrainingdate(
            @RequestBody TrainingdateDTO TrainingdateDTO
    ){
        return ResponseEntity.ok(trainingService.createNewTrainingdate(TrainingdateDTO));
    }

    @PutMapping("/update/{id}")
    public void updateTrainingdate(
            @PathVariable Long id,
            @RequestBody TrainingdateDTO Trainingdate
    ){
        trainingService.updateTrainingdate(id, Trainingdate);
        ResponseEntity.ok();
    }
}

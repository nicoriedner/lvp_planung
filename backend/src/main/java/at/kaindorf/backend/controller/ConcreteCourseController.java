package at.kaindorf.backend.controller;

import at.kaindorf.backend.dto.ConcreteCourseDTO;
import at.kaindorf.backend.dto.ConcreteCourseDetailsDTO;
import at.kaindorf.backend.dto.WeeklyCoursesDTO;
import at.kaindorf.backend.model.Classroom;
import at.kaindorf.backend.model.Trainee;
import at.kaindorf.backend.model.Trainer;
import at.kaindorf.backend.service.ConcreteCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/concrete-course")
@RequiredArgsConstructor
public class ConcreteCourseController {
    private final ConcreteCourseService concreteCourseService;

    @GetMapping
    public ResponseEntity<List<ConcreteCourseDTO>> getAllConcreteCourses() {
        return ResponseEntity.ok(concreteCourseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConcreteCourseDTO> getConcreteCourse(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(concreteCourseService.findById(id));
    }

    @GetMapping("/trainer/{trainer}")
    public ResponseEntity<List<ConcreteCourseDTO>> getAllConcreteCourseByTrainer(
            @PathVariable Trainer trainer
    ){
        return ResponseEntity.ok(concreteCourseService.findAllByTrainer(trainer));
    }

    @GetMapping("/allByTrainees/{trainee}")
    public ResponseEntity<List<ConcreteCourseDTO>> getAllByTrainee(
            @PathVariable Trainee trainee
    ){
        return ResponseEntity.ok(concreteCourseService.findAllByTrainees(trainee));
    }

    @GetMapping("/allByClassroom/{classroom}")
    public ResponseEntity<List<ConcreteCourseDTO>> getAllByClassroom(
            @PathVariable Classroom classroom
    ){
        return ResponseEntity.ok(concreteCourseService.findAllByClassroom(classroom));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteConcreteCourse(
            @PathVariable Long id
    ){
        concreteCourseService.delete(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createConcreteCourse(
            @RequestBody ConcreteCourseDTO concreteCourseDTO
    ){
        return ResponseEntity.ok(concreteCourseService.create(concreteCourseDTO));
    }

    @PutMapping("/update/{id}")
    public void updateConcreteCourse(
            @PathVariable Long id,
            @RequestBody ConcreteCourseDTO concreteCourseDTO
    ){
        concreteCourseService.update(id, concreteCourseDTO);
        ResponseEntity.ok();
    }

    @GetMapping("/course-info/{weekKey}")
    public List<WeeklyCoursesDTO> getConcreteCoursesForWeekKey(@PathVariable String weekKey) {
        try {
            List<WeeklyCoursesDTO> courses = concreteCourseService.getConcreteCoursesForWeekKey(weekKey);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }
/*
    @GetMapping("/course-details/{id}")
    public ConcreteCourseDetailsDTO getConcreteCourseDetails(@PathVariable long id) {
        return concreteCourseService.getConcreteCourseDetails(id);
    }*/
}

package at.kaindorf.backend.controller;

import at.kaindorf.backend.dto.ClassroomDTO;
import at.kaindorf.backend.dto.ConcreteCourseDTO;
import at.kaindorf.backend.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/classroom")
@RequiredArgsConstructor
public class ClassroomController {
    private final ClassroomService classroomService;

    @GetMapping
    public ResponseEntity<List<ClassroomDTO>> getAllClassrooms() {
        return ResponseEntity.ok(classroomService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomDTO> getClassroomById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(classroomService.findById(id));
    }

    @GetMapping("/minSeating/{minNum}")
    public ResponseEntity<List<ClassroomDTO>> getClassroomByMinSeating(
            @PathVariable int minNum
    ){
        return ResponseEntity.ok(classroomService.findAllWithMinSeating(minNum));
    }

    @GetMapping("/name/{description}")
    public ResponseEntity<ClassroomDTO> getClassroomByDescription(
            @PathVariable String description
    ){
        return ResponseEntity.ok(classroomService.findByName(description));
    }

    @PostMapping("/book")
    public ResponseEntity<Long> bookClassroom(
            @RequestBody ConcreteCourseDTO concreteCourseDTO
    ){
        return ResponseEntity.ok(classroomService.bookClassroom(concreteCourseDTO));
    }

    @PostMapping("/deleteBooking/{date}")
    public void deleteBooking(
            @RequestBody ConcreteCourseDTO concreteCourseDTO
    ){
        classroomService.deleteBooking(concreteCourseDTO);
        ResponseEntity.ok();
    }

    @PutMapping("/updateBooking/{id}")
    public void updateBooking(
            @PathVariable Long id,
            @RequestBody ConcreteCourseDTO concreteCourseDTO
    ){
        classroomService.updateBooking(id, concreteCourseDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createClassroom(
            @RequestBody ClassroomDTO classroomDTO
    ){
        return ResponseEntity.ok(classroomService.createNewClassroom(classroomDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClassroom(
            @PathVariable Long id
    ){
        classroomService.deleteClassroom(id);
    }

    @PostMapping("/update/{id}")
    public void updateClassroom(
            @PathVariable Long id,
            @RequestBody ClassroomDTO classroomDTO
    ){
        classroomService.updateClassroom(id, classroomDTO);
        ResponseEntity.ok();
    }
}

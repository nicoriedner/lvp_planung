package at.kaindorf.backend.controller;

import at.kaindorf.backend.dto.CourseDTO;
import at.kaindorf.backend.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(courseService.findCourseById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CourseDTO> getCourseByName(
            @PathVariable String name
    ){
        return ResponseEntity.ok(courseService.findCourseByName(name));
    }

    @GetMapping("/duration/{duration}")
    public ResponseEntity<List<CourseDTO>> getCoursesByDuration(
            @PathVariable Integer duration
    ){
        return ResponseEntity.ok(courseService.findCourseByDuration(duration));
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createCourse(
            @RequestBody CourseDTO courseDTO
    ){
        return ResponseEntity.ok(courseService.createNewCourse(courseDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCourse(
            @PathVariable Long id
    ){
        courseService.deleteCourse(id);
    }

    @PutMapping("/update/{id}")
    public void updateCourse(
            @PathVariable Long id,
            @RequestBody CourseDTO courseDTO
    ){
        courseService.updateCourse(id, courseDTO);
        ResponseEntity.ok();
    }

    @PostMapping
    public String getCourseDetails(@RequestBody long courseId) {
        StringBuilder sb = new StringBuilder();

        CourseDTO course = courseService.findCourseById(courseId);

        return null;
    }
}

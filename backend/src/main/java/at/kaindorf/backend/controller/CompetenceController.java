package at.kaindorf.backend.controller;

import at.kaindorf.backend.dto.CompetenceDTO;
import at.kaindorf.backend.service.CompetenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/competence")
@RequiredArgsConstructor
public class CompetenceController {

    private final CompetenceService competenceService;

    @GetMapping
    public ResponseEntity<List<CompetenceDTO>> getAllCompetenceen() {
        return ResponseEntity.ok(competenceService.findAllCompetences());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CompetenceDTO> getCompetenceByName(
            @PathVariable String name
    ){
    return ResponseEntity.ok(competenceService.findCompetenceByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetenceDTO> getCompetenceById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(competenceService.findCompetenceById(id));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCompetenceById(
            @PathVariable Long id
    ){
        competenceService.deleteCompetenceById(id);
    }
}

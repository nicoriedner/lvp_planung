package at.kaindorf.backend.controller;

import at.kaindorf.backend.dto.RessourceDTO;
import at.kaindorf.backend.model.Ressource;
import at.kaindorf.backend.model.RessourcenTyp;
import at.kaindorf.backend.repositories.RessourceRepository;
import at.kaindorf.backend.service.RessourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/ressource")
@RequiredArgsConstructor
public class RessourceController {

    private final RessourceService ressourceService;

    @GetMapping
    public ResponseEntity<List<RessourceDTO>> getRessource() {
        return ResponseEntity.ok(ressourceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RessourceDTO> getRessourceById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(ressourceService.findById(id));
    }

    @GetMapping("/available")
    public ResponseEntity<List<RessourceDTO>> getAllAvailableRessources() {
        return ResponseEntity.ok(ressourceService.findAvailableRessources());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<RessourceDTO>> getAllRessourcesByType(
            @PathVariable RessourcenTyp type
    ){
        return ResponseEntity.ok(ressourceService.findRessourcesByType(type));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<RessourceDTO>> getAllRessourcesByName(
            @PathVariable String name
    ){
        return ResponseEntity.ok(ressourceService.findRessourcesByName(name));
    }

    @GetMapping("/inventar/{nr}")
    public ResponseEntity<RessourceDTO> getAllRessourcesByInventarNr(
            @PathVariable Long nr
    ){
        return ResponseEntity.ok(ressourceService.findRessourceByInventarNr(nr));
    }

    @PostMapping("/delete/{id}")
    public void deleteRessource(
            @PathVariable Long id
    ){
        ressourceService.deleteRessource(id);
        ResponseEntity.ok();
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createRessource(
            @RequestBody RessourceDTO ressource
    ){
        return ResponseEntity.ok(ressourceService.createNewRessource(ressource));
    }

    @PostMapping("/update/{id}")
    public void updateRessource(
            @PathVariable Long id,
            @RequestBody RessourceDTO ressource
    ){
        ressourceService.updateRessource(id, ressource);
        ResponseEntity.ok();
    }
}

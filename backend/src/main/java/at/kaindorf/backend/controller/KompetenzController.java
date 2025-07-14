package at.kaindorf.backend.controller;

import at.kaindorf.backend.dto.KompetenzDTO;
import at.kaindorf.backend.service.KompetenzService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/kompetenz")
@RequiredArgsConstructor
public class KompetenzController {

    private final KompetenzService kompetenzService;

    @GetMapping
    public ResponseEntity<List<KompetenzDTO>> getAllKompetenzen() {
        return ResponseEntity.ok(kompetenzService.findAllKompetenzen());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<KompetenzDTO> getKompetenzByName(
            @PathVariable String name
    ){
        return ResponseEntity.ok(kompetenzService.findKompetenzByBezeichnung(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<KompetenzDTO> getKompetenzById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(kompetenzService.findKompetenzById(id));
    }
}

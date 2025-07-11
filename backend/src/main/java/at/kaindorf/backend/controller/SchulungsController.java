package at.kaindorf.backend.controller;

import at.kaindorf.backend.dto.SchulungsterminDTO;
import at.kaindorf.backend.model.Lehrgang;
import at.kaindorf.backend.model.Lehrsaal;
import at.kaindorf.backend.model.Person;
import at.kaindorf.backend.model.Schulungstermin;
import at.kaindorf.backend.service.LehrsaalService;
import at.kaindorf.backend.service.SchulungsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schulung")
public class SchulungsController {
    private final SchulungsService schulungsService;

    @GetMapping
    public ResponseEntity<List<SchulungsterminDTO>> getAllSchulungstermine() {
        return ResponseEntity.ok(schulungsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchulungsterminDTO> getSchulungstermine(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(schulungsService.findById(id));
    }

    @GetMapping("/leiter/{leiter}")
    public ResponseEntity<List<SchulungsterminDTO>> getAllSchulungsTermineByLeiter(
            @PathVariable Person leiter
            ){
        return ResponseEntity.ok(schulungsService.findAllByLeiter(leiter));
    }

    @GetMapping("/allByTeilnehmer/{teilnehmer}")
    public ResponseEntity<List<SchulungsterminDTO>> getAllByTeilnehmer(
            @PathVariable Person teilnehmer
    ){
     return ResponseEntity.ok(schulungsService.findAllByTeilnehmer(teilnehmer));
    }

    @GetMapping("/allByLehrgang/{lehrgang}")
    public ResponseEntity<List<SchulungsterminDTO>> getAllByLehrgang(
            @PathVariable Lehrgang lehrgang
    ){
        return ResponseEntity.ok(schulungsService.findAllByLehrgang(lehrgang));
    }

    @GetMapping("/allByLehrsaal/{lehrsaal}")
    public ResponseEntity<List<SchulungsterminDTO>> getAllByLehrsaal(
            @PathVariable Lehrsaal lehrsaal
            ){
        return ResponseEntity.ok(schulungsService.findAllByLehrsaal(lehrsaal));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSchulungstermin(
            @PathVariable Long id
    ){
        schulungsService.deleteSchulungstermin(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createSchulungstermin(
            @RequestBody SchulungsterminDTO schulungsterminDTO
    ){
        return ResponseEntity.ok(schulungsService.createNewSchulungstermin(schulungsterminDTO));
    }

    @PutMapping("/update/{id}")
    public void updateSchulungstermin(
            @PathVariable Long id,
            @RequestBody SchulungsterminDTO schulungstermin
    ){
        schulungsService.updateSchulungstermin(id, schulungstermin);
        ResponseEntity.ok();
    }
}

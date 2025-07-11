package at.kaindorf.backend.controller;

import at.kaindorf.backend.dto.LehrgangDTO;
import at.kaindorf.backend.model.Lehrgang;
import at.kaindorf.backend.repositories.LehrgangRepository;
import at.kaindorf.backend.service.LehrgangService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/lehrgang")
@RequiredArgsConstructor
public class LehrgangController {
    private final LehrgangService lehrgangService;

    @GetMapping
    public ResponseEntity<List<LehrgangDTO>> getAllLehrgaenge() {
        return ResponseEntity.ok(lehrgangService.findAllLehrgaenge());
    }

    @GetMapping("/{bezeichnung}")
    public ResponseEntity<LehrgangDTO> getLehrgangByBezeichnung(
            @PathVariable String bezeichnung
    ){
        return ResponseEntity.ok(lehrgangService.findLehrgangByBezeichnung(bezeichnung));
    }

    @GetMapping("/dauer/{dauer}")
    public ResponseEntity<List<LehrgangDTO>> getLehrgaengeByDauer(
            @PathVariable Integer dauer
    ) {
        return ResponseEntity.ok(lehrgangService.findLehrgangByDauer(dauer));
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createLehrgang(
            @RequestBody LehrgangDTO lehrgangDTO
    ){
        return ResponseEntity.ok(lehrgangService.createNewLehrgang(lehrgangDTO));
    }

    @PostMapping("/delete/{id}")
    public void deleteLehrgang(
            @PathVariable Long id
    ){
        lehrgangService.deleteLehrgang(id);
        ResponseEntity.ok();
    }

    @PostMapping("/update/{id}")
    public void updateLehrgang(
            @PathVariable Long id,
            @RequestBody LehrgangDTO lehrgangDTO
    ){
        lehrgangService.updateLehrgang(id, lehrgangDTO);
        ResponseEntity.ok();
    }
}

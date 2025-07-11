package at.kaindorf.backend.controller;

import at.kaindorf.backend.dto.LehrsaalDTO;
import at.kaindorf.backend.dto.SchulungsterminDTO;
import at.kaindorf.backend.model.Lehrsaal;
import at.kaindorf.backend.model.Schulungstermin;
import at.kaindorf.backend.service.LehrsaalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/lehrsaal")
@RequiredArgsConstructor
public class LehrsaalController {
    private final LehrsaalService lehrsaalService;

    @GetMapping
    public ResponseEntity<List<LehrsaalDTO>> getAllLehrsaele() {
        return ResponseEntity.ok(lehrsaalService.findAll());
    }

    @GetMapping("/minPlaetze/{minAnz}")
    public ResponseEntity<List<LehrsaalDTO>> getLehrsaalByMaxPlaetze(
            @PathVariable int minAnz
    ) {
        return ResponseEntity.ok(lehrsaalService.findAllWithMinSitzPlaetze(minAnz));
    }

    @GetMapping("/{bezeichnung}")
    public ResponseEntity<LehrsaalDTO> getLehrsaalByBezeichnung(
            @PathVariable String bezeichnung
    ){
        return ResponseEntity.ok(lehrsaalService.findByBezeichnung(bezeichnung));
    }

    @GetMapping("/termin/{termin}")
    public ResponseEntity<List<LehrsaalDTO>> getLehrsaalBySchulungstermin(
            @PathVariable Schulungstermin termin
    ){
        return ResponseEntity.ok(lehrsaalService.findBySchulungstermin(termin));
    }

    @PostMapping("/book")
    public ResponseEntity<Long> bookLehrsaal(
            @RequestBody SchulungsterminDTO schulungsterminDTO
    ){
        return ResponseEntity.ok(lehrsaalService.bookLehrsaal(schulungsterminDTO));
    }

    @PostMapping("/deleteBooking/{termin}")
    public void deleteBooking(
            @RequestBody SchulungsterminDTO schulungsterminDTO
    ){
        lehrsaalService.deleteBooking(schulungsterminDTO);
        ResponseEntity.ok();
    }

    @PostMapping("/updatBooking/{id}")
    public void updateBooking(
            @PathVariable Long id,
            @RequestBody SchulungsterminDTO schulungsterminDTO
    ){
        lehrsaalService.updateBooking(id, schulungsterminDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createLehrsaal(
            @RequestBody LehrsaalDTO lehrsaalDTO
    ){
        return ResponseEntity.ok(lehrsaalService.createNewLehrsaal(lehrsaalDTO));
    }

    @PostMapping("/delete/{id}")
    public void deleteLehrsaal(
            @PathVariable Long id
    ){
        lehrsaalService.deleteLehrsaal(id);
        ResponseEntity.ok();
    }

    @PostMapping("/update/{id}")
    public void updateLehrsaal(
            @PathVariable Long id,
            @RequestBody LehrsaalDTO lehrsaalDTO
    ){
        lehrsaalService.updateLehrsaal(id, lehrsaalDTO);
        ResponseEntity.ok();
    }
}

package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.LehrsaalDTO;
import at.kaindorf.backend.dto.SchulungsterminDTO;
import at.kaindorf.backend.mapper.LehrsaalMapper;
import at.kaindorf.backend.mapper.SchulungsterminMapper;
import at.kaindorf.backend.model.Lehrsaal;
import at.kaindorf.backend.model.Schulungstermin;
import at.kaindorf.backend.model.Status;
import at.kaindorf.backend.repositories.LehrsaalRepository;
import at.kaindorf.backend.repositories.SchulungsterminRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class LehrsaalService {
    private static final Logger log = LoggerFactory.getLogger(LehrsaalService.class);
    private final LehrsaalRepository lehrsaalRepository;
    private final LehrsaalMapper lehrsaalMapper;
    private final SchulungsterminRepository schulungsterminRepository;
    private final SchulungsterminMapper schulungsterminMapper;

    public List<LehrsaalDTO> findAll() {
        List<Lehrsaal> lehrsaele = lehrsaalRepository.findAll();
        return lehrsaele.stream()
                .map(lehrsaalMapper::toDTO)
                .toList();
    }

    public List<LehrsaalDTO> findAllWithMinSitzPlaetze(int minSitzPlaetze) {
        List<Lehrsaal> lehrsaele = lehrsaalRepository.findLehrsaalBySitzPlaetzeGreaterThanEqual(minSitzPlaetze);
        return lehrsaele.stream()
                .map(lehrsaalMapper::toDTO)
                .toList();
    }

    public LehrsaalDTO findByBezeichnung(String bezeichnung) {
        Lehrsaal lehrsaal = lehrsaalRepository.findLehrsaalByBezeichnung(bezeichnung);
        return lehrsaalMapper.toDTO(lehrsaal);
    }

    public List<LehrsaalDTO> findBySchulungstermin(Schulungstermin schulungstermin) {
        return lehrsaalRepository.findLehrsaalBySchulungstermine(schulungstermin)
                .stream()
                .map(lehrsaalMapper::toDTO)
                .toList();
    }

    public Long createNewLehrsaal(LehrsaalDTO lehrsaalDTO) {
        Lehrsaal lehrsaal = lehrsaalMapper.toEntity(lehrsaalDTO);
        return lehrsaalRepository.save(lehrsaal).getId();
    }

    public boolean bookLehrsaal(SchulungsterminDTO creation) {
        Lehrsaal lehrsaal = lehrsaalRepository.findLehrsaalById(creation.getLehrsaal().getId());

        if (lehrsaal == null) {
            System.out.println("Lehrsaal mit ID " + creation.getLehrsaal().getId() + " nicht gefunden.");
            return false;
        }

        List<Schulungstermin> existingSchulungstermine = lehrsaal.getSchulungstermine();
        LocalDateTime newStart = creation.getStartDatum();
        LocalDateTime newEnd = creation.getEndDatum();

        if (newStart == null || newEnd == null || newStart.isAfter(newEnd)) {
            System.out.println("Ungültiger Zeitraum für den neuen Termin.");
            return false;
        }

        for (Schulungstermin existingSchulungstermin : existingSchulungstermine) {
            LocalDateTime existingStart = existingSchulungstermin.getStartDatum();
            LocalDateTime existingEnd = existingSchulungstermin.getEndDatum();

            if (existingStart == null || existingEnd == null) {
                log.info("Schulungstermin ID " + existingSchulungstermin.getId() + " hat ungültige Daten.");
                continue;
            }

            if (newStart.isBefore(existingEnd) && newEnd.isAfter(existingStart)) {
                log.info("Lehrsaal " + lehrsaal.getBezeichnung() + " ist bereits belegt (Überlappung mit Termin ID: " + existingSchulungstermin.getId() + ").");
                return false;
            }
        }

        log.info("Lehrsaal " + lehrsaal.getBezeichnung() + " ist verfügbar für den Zeitraum " + newStart + " bis " + newEnd + ".");

    Schulungstermin newBooking = schulungsterminMapper.toEntity(creation);
    newBooking.setLehrsaal(lehrsaal);
    newBooking.setStatus(Status.GEPLANT);
    schulungsterminRepository.save(newBooking);

        return true;
    }
}
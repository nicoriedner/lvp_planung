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
import jakarta.persistence.EntityNotFoundException;
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

    public Long bookLehrsaal(SchulungsterminDTO creation) {
        Lehrsaal lehrsaal = lehrsaalRepository.findLehrsaalById(creation.getLehrsaal().getId());

        if (lehrsaal == null) {
            throw new RuntimeException("Lehrsaal mit ID " + creation.getLehrsaal().getId() + " nicht gefunden.");
        }

        List<Schulungstermin> existingSchulungstermine = lehrsaal.getSchulungstermine();
        LocalDateTime newStart = creation.getStartDatum();
        LocalDateTime newEnd = creation.getEndDatum();

        if (newStart == null || newEnd == null || newStart.isAfter(newEnd)) {
            throw new RuntimeException("Ungültiger Zeitraum für den neuen Termin.");
        }

        for (Schulungstermin existingSchulungstermin : existingSchulungstermine) {
            LocalDateTime existingStart = existingSchulungstermin.getStartDatum();
            LocalDateTime existingEnd = existingSchulungstermin.getEndDatum();

            if (existingStart == null || existingEnd == null) {
                throw new RuntimeException("Schulungstermin ID " + existingSchulungstermin.getId() + " hat ungültige Daten.");
            }

            if (newStart.isBefore(existingEnd) && newEnd.isAfter(existingStart)) {
                throw new RuntimeException("Lehrsaal " + lehrsaal.getBezeichnung() + " ist bereits belegt (Überlappung mit Termin ID: " + existingSchulungstermin.getId() + ").");
            }
        }

        log.info("Lehrsaal " + lehrsaal.getBezeichnung() + " ist verfügbar für den Zeitraum " + newStart + " bis " + newEnd + ".");

    Schulungstermin newBooking = schulungsterminMapper.toEntity(creation);
    newBooking.setLehrsaal(lehrsaal);
    newBooking.setStatus(Status.GEPLANT);
    newBooking = schulungsterminRepository.save(newBooking);
        return newBooking.getId();

    }

    public void deleteBooking(SchulungsterminDTO dto) {
        Lehrsaal lehrsaal = lehrsaalRepository.findLehrsaalById(dto.getLehrsaal().getId());
        if (lehrsaal == null) {
            throw new RuntimeException("Der Lehrsaal mit der ID " + lehrsaal.getId() + " existiert nicht.");
        }

        Schulungstermin termin = schulungsterminRepository.findSchulungsterminByLehrsaalAndStartDatumAndEndDatum(
                lehrsaal, dto.getStartDatum(), dto.getEndDatum());

        if (termin != null) {
            schulungsterminRepository.delete(termin);
            log.info("Schulungstermin erfolgreich gelöscht.");
        }
    }

    public void updateBooking(Long id, SchulungsterminDTO schulungsterminDTO)
    {
        if (!schulungsterminRepository.existsById(id)) {
            throw new EntityNotFoundException("Schulungstermin mit ID " + id + " existiert nicht.");
        }

        Schulungstermin schulungstermin = schulungsterminRepository.findById(id).get();
        Schulungstermin newSchulungstermin = schulungsterminMapper.toEntity(schulungsterminDTO);

        newSchulungstermin.setId(schulungstermin.getId());
        schulungsterminRepository.save(newSchulungstermin);
    }

    public Long createNewLehrsaal(LehrsaalDTO lehrsaalDTO) {
        Lehrsaal lehrsaal = lehrsaalMapper.toEntity(lehrsaalDTO);
        return lehrsaalRepository.save(lehrsaal).getId();
    }

    public void deleteLehrsaal(Long id) {
        if(!lehrsaalRepository.findById(id).isPresent()) {
            throw new EntityNotFoundException("Lehrsaal mit ID " + id + " existiert nicht.");
        }
        lehrsaalRepository.deleteById(id);
    }

    public void updateLehrsaal(Long id, LehrsaalDTO lehrsaalDTO) {
        if(!lehrsaalRepository.findById(id).isPresent()) {
            throw new EntityNotFoundException("Lehrsaal mit ID " + id + " existiert nicht.");
        }
        Lehrsaal lehrsaal = lehrsaalRepository.findById(id).get();
        Lehrsaal newLehrsaal = lehrsaalMapper.toEntity(lehrsaalDTO);
        newLehrsaal.setId(id);
        lehrsaalRepository.save(newLehrsaal);
    }
}
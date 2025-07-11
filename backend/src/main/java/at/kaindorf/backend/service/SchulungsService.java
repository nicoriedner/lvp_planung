package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.SchulungsterminDTO;
import at.kaindorf.backend.exceptions.SchulungsterminNotFoundException;
import at.kaindorf.backend.mapper.SchulungsterminMapper;
import at.kaindorf.backend.model.Lehrgang;
import at.kaindorf.backend.model.Lehrsaal;
import at.kaindorf.backend.model.Person;
import at.kaindorf.backend.model.Schulungstermin;
import at.kaindorf.backend.repositories.SchulungsterminRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SchulungsService {
    private final SchulungsterminMapper schulungsMapper;
    private final SchulungsterminRepository schulungsRepository;
    private final SchulungsterminRepository schulungsterminRepository;

    public List<SchulungsterminDTO> findAll() {
        return (schulungsRepository.findAll()
                .stream()
                .map(schulungsMapper::toDTO)
                .toList());
    }

    public List<SchulungsterminDTO> findAllByLeiter(Person leiter) {
        return (schulungsRepository.findSchulungstermineByLeiter(leiter)
                .stream()
                .map(schulungsMapper::toDTO).
                toList());
    }

    public List<SchulungsterminDTO> findAllByTeilnehmer(Person teilnehmer) {
        return (schulungsRepository.findAll()
                .stream().filter(s -> s.getTeilnehmer().contains(teilnehmer))
                .map(schulungsMapper::toDTO)
                .toList());
    }

    public List<SchulungsterminDTO> findAllByLehrgang(Lehrgang lehrgang) {
        return (schulungsRepository.findSchulungstermineByLehrgang(lehrgang)
                .stream()
                .map(schulungsMapper::toDTO)
                .toList());
    }

    public List<SchulungsterminDTO> findAllByLehrsaal(Lehrsaal lehrsaal) {
        return (schulungsRepository.findSchulungstermineByLehrsaal(lehrsaal)
                .stream()
                .map(schulungsMapper::toDTO)
                .toList());
    }

    public void deleteSchulungstermin(Long id) {
            Schulungstermin termin = schulungsRepository.findById(id)
                    .orElseThrow(() -> new SchulungsterminNotFoundException(id));
            schulungsRepository.delete(termin);
        }

    public Long createNewSchulungstermin(SchulungsterminDTO schulungsterminDTO) {
        Schulungstermin schulungstermin = schulungsRepository.save(schulungsMapper.toEntity(schulungsterminDTO));
        return schulungstermin.getId();
    }

    public void updateSchulungstermin(Long id, SchulungsterminDTO schulungsterminDTO) {
        Schulungstermin schulungstermin = schulungsterminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Schulungstermin mit ID " + id + " existiert nicht!"));
        Schulungstermin newSchulungstermin = schulungsMapper.toEntity(schulungsterminDTO);

        schulungstermin.setStatus(newSchulungstermin.getStatus());
        schulungstermin.setLehrsaal(newSchulungstermin.getLehrsaal());
        schulungstermin.setLehrgang(newSchulungstermin.getLehrgang());
        schulungstermin.setAnzTeilnehmer(newSchulungstermin.getAnzTeilnehmer());
        schulungstermin.setStartDatum(newSchulungstermin.getStartDatum());
        schulungstermin.setEndDatum(newSchulungstermin.getEndDatum());
        schulungstermin.setLeiter(newSchulungstermin.getLeiter());
        schulungstermin.setRessource(newSchulungstermin.getRessource());
        schulungstermin.setTeilnehmer(newSchulungstermin.getTeilnehmer());
        schulungsterminRepository.save(schulungstermin);
    }
}

package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.KompetenzDTO;
import at.kaindorf.backend.mapper.KompetenzMapper;
import at.kaindorf.backend.model.Kompetenz;
import at.kaindorf.backend.repositories.KompetenzRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KompetenzService {
    private final KompetenzRepository kompetenzRepository;
    private final KompetenzMapper kompetenzMapper;

    public List<KompetenzDTO> getAllKompetenzen() {
        return kompetenzRepository.findAll()
                .stream().map(kompetenzMapper::toDTO)
                .toList();
    }

    public KompetenzDTO getKompetenzByBezeichnung(String bezeichnung) {
        return kompetenzMapper.toDTO(kompetenzRepository.findByBezeichnung(bezeichnung));
    }
}

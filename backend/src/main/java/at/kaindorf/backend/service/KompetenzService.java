package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.KompetenzDTO;
import at.kaindorf.backend.exceptions.KompetenzNotFoundException;
import at.kaindorf.backend.mapper.KompetenzMapper;
import at.kaindorf.backend.repositories.KompetenzRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KompetenzService {
    private final KompetenzRepository kompetenzRepository;
    private final KompetenzMapper kompetenzMapper;

    public List<KompetenzDTO> findAllKompetenzen() {
        return kompetenzRepository.findAll()
                .stream().map(kompetenzMapper::toDTO)
                .toList();
    }

    public KompetenzDTO findKompetenzByName(String name) {
        kompetenzRepository.findByName(name);
        return kompetenzMapper.toDTO(kompetenzRepository.findByName(name));
    }

    public KompetenzDTO findKompetenzById(Long id) {
        if(!kompetenzRepository.findById(id).isPresent()) {
            throw new KompetenzNotFoundException(id);
        }
        return kompetenzMapper.toDTO(kompetenzRepository.findById(id).orElse(null));
    }
}

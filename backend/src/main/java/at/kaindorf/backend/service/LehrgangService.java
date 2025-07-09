package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.LehrgangDTO;
import at.kaindorf.backend.mapper.LehrgangMapper;
import at.kaindorf.backend.model.Lehrgang;
import at.kaindorf.backend.repositories.LehrgangRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LehrgangService {
    private final LehrgangRepository lehrgangRepository;
    private final LehrgangMapper lehrgangMapper;

    public List<LehrgangDTO> findAllLehrgaenge() {
        List<Lehrgang> lehrgaenge = lehrgangRepository.findAll();
        return lehrgaenge.stream().map(lehrgangMapper::toDTO).toList();
    }

    public LehrgangDTO findLehrgangByBezeichnung(String bezeichnung) {
        return lehrgangMapper.toDTO(lehrgangRepository.findLehrgangByBezeichnung(bezeichnung));
    }

    public List<LehrgangDTO> findLehrgangByDauer(Integer dauer) {
        return lehrgangRepository.findLehrgaengeByDauer(dauer)
                .stream()
                .map(lehrgangMapper::toDTO)
                .toList();
    }
}

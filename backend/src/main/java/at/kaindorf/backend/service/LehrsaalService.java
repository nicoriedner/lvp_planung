package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.LehrsaalDTO;
import at.kaindorf.backend.mapper.LehrsaalMapper;
import at.kaindorf.backend.model.Lehrsaal;
import at.kaindorf.backend.repositories.LehrsaalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class LehrsaalService {
    private final LehrsaalRepository lehrsaalRepository;
    private final LehrsaalMapper lehrsaalMapper;

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

    public List<LehrsaalDTO> findBySchulungstermin(LocalDate schulungstermin) {
        return lehrsaalRepository.findAll()
                .stream().filter(l -> l.getSchulungstermine().contains(schulungstermin))
                .map(lehrsaalMapper::toDTO)
                .toList();
    }
}

package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.LehrgangDTO;
import at.kaindorf.backend.exceptions.LehrgangNotFoundException;
import at.kaindorf.backend.mapper.LehrgangMapper;
import at.kaindorf.backend.model.Lehrgang;
import at.kaindorf.backend.repositories.LehrgangRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public Long createNewLehrgang(LehrgangDTO lehrgangDTO) {
        Lehrgang lehrgang = lehrgangMapper.toEntity(lehrgangDTO);
        return lehrgangRepository.save(lehrgang).getId();
    }

    public void deleteLehrgang(Long id) {
        if(lehrgangRepository.existsById(id)) {
            lehrgangRepository.deleteById(id);
        } else {
            throw new LehrgangNotFoundException(id);
        }
    }

    public void updateLehrgang(Long id, LehrgangDTO lehrgangDTO) {
        if(lehrgangRepository.existsById(id)) {
            Lehrgang lehrgang = lehrgangRepository.findById(id).get();
            Lehrgang newLehrgang = lehrgangMapper.toEntity(lehrgangDTO);
            newLehrgang.setId(lehrgang.getId());
            lehrgangRepository.save(newLehrgang);
        } else {
            throw new LehrgangNotFoundException(id);
        }
    }
}

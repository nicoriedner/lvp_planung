package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.RessourceDTO;
import at.kaindorf.backend.exceptions.RessourceNotFoundException;
import at.kaindorf.backend.mapper.RessourceMapper;
import at.kaindorf.backend.model.Ressource;
import at.kaindorf.backend.model.RessourcenTyp;
import at.kaindorf.backend.repositories.RessourceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RessourceService {
    private final RessourceMapper ressourceMapper;
    private final RessourceRepository ressourceRepository;

    public List<RessourceDTO> findAll() {
        return ressourceRepository.findAll()
                .stream()
                .map(ressourceMapper::toDTO)
                .toList();
    }

    public RessourceDTO findById(Long id) {
        return ressourceMapper.toDTO(ressourceRepository.findRessourceById(id));
    }

    public List<RessourceDTO> findAvailableRessources() {
        return ressourceRepository.findAvailableRessources()
                .stream()
                .map(ressourceMapper::toDTO)
                .toList();
    }

    public List<RessourceDTO> findRessourcesByType(RessourcenTyp ressourcenTyp) {
        return ressourceRepository.findRessourceByTyp(ressourcenTyp)
                .stream()
                .map(ressourceMapper::toDTO)
                .toList();
    }

    public List<RessourceDTO> findRessourcesByName(String bezeichnung) {
        return ressourceRepository.findRessourceByBezeichnung(bezeichnung)
                .stream()
                .map(ressourceMapper::toDTO)
                .toList();
    }

    public RessourceDTO findRessourceByInventarNr(Long inventarNr) {
        return ressourceMapper.toDTO(ressourceRepository.findRessourceByInventarNr(inventarNr));
    }

    public Long createNewRessource(RessourceDTO ressourceDTO) {
        Ressource ressource = ressourceMapper.toEntity(ressourceDTO);
        return ressourceRepository.save(ressource).getId();
    }

    public void deleteRessource(Long id) {
        if(ressourceRepository.findById(id) == null) {
            throw new RessourceNotFoundException(id);
        }
        ressourceRepository.deleteById(id);
    }

    public void updateRessource(Long id, RessourceDTO ressourceDTO) {
        if(!ressourceRepository.existsById(id)) {
            throw new RessourceNotFoundException(id);
        }
        Ressource ressource = ressourceMapper.toEntity(ressourceDTO);
        ressource.setId(id);
        ressourceRepository.save(ressource);
    }
}

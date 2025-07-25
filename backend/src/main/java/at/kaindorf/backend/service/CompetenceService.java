package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.CompetenceDTO;
import at.kaindorf.backend.exceptions.CompetenceNotFoundException;
import at.kaindorf.backend.mapper.CompetenceMapper;
import at.kaindorf.backend.model.Competence;
import at.kaindorf.backend.repositories.CompetenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompetenceService {
    private final CompetenceRepository competenceRepository;
    private final CompetenceMapper competenceMapper;

    public List<CompetenceDTO> findAllCompetences() {
        return competenceRepository.findAll()
                .stream().map(competenceMapper::toDTO)
                .toList();
    }

    public CompetenceDTO findCompetenceByName(String name) {
        competenceRepository.findByName(name);
        return competenceMapper.toDTO(competenceRepository.findByName(name));
    }

    public CompetenceDTO findCompetenceById(Long id) {
        Competence competence = competenceRepository.findById(id)
                .orElseThrow(() -> new CompetenceNotFoundException(id));
        return competenceMapper.toDTO(competence);
    }

    public void deleteCompetenceById(Long id) {
        Competence competence = competenceRepository.findById(id)
                .orElseThrow(() -> new CompetenceNotFoundException(id));
        competenceRepository.delete(competence);
    }
}

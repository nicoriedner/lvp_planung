package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.ResourceDTO;
import at.kaindorf.backend.exceptions.ResourceNotFoundException;
import at.kaindorf.backend.mapper.ResourceMapper;
import at.kaindorf.backend.model.Resource;
import at.kaindorf.backend.model.ResourceType;
import at.kaindorf.backend.repositories.ResourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ResourceService {
    private final ResourceMapper resourceMapper;
    private final ResourceRepository resourceRepository;

    public List<ResourceDTO> findAll() {
        return resourceRepository.findAll()
                .stream()
                .map(resourceMapper::toDTO)
                .toList();
    }

    public ResourceDTO findById(Long id) {
        if(!resourceRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException(id);
        }
        return resourceMapper.toDTO(resourceRepository.findResourceById(id));
    }

    public List<ResourceDTO> findAvailableResources() {
        return resourceRepository.findAvailableResources()
                .stream()
                .map(resourceMapper::toDTO)
                .toList();
    }

    public List<ResourceDTO> findResourcesByType(ResourceType resourceType) {
        return resourceRepository.findResourceByTyp(resourceType)
                .stream()
                .map(resourceMapper::toDTO)
                .toList();
    }

    public List<ResourceDTO> findResourcesByName(String name) {
        return resourceRepository.findResourceByName(name)
                .stream()
                .map(resourceMapper::toDTO)
                .toList();
    }

    public ResourceDTO findResourceByInventoryNr(Long inventoryNum) {
        return resourceMapper.toDTO(resourceRepository.findResourceByInventoryNum(inventoryNum));
    }

    public Long createNewResource(ResourceDTO resourceDTO) {
        Resource resource = resourceMapper.toEntity(resourceDTO);
        return resourceRepository.save(resource).getId();
    }

    public void deleteResource(Long id) {
        if(!resourceRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException(id);
        }
        resourceRepository.deleteById(id);
    }

    public void updateResource(Long id, ResourceDTO resourceDTO) {
        if(!resourceRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        Resource resource = resourceMapper.toEntity(resourceDTO);
        resource.setId(id);
        resourceRepository.save(resource);
    }
}

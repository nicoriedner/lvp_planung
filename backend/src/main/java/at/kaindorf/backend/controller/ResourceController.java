package at.kaindorf.backend.controller;

import at.kaindorf.backend.dto.ResourceDTO;
import at.kaindorf.backend.model.ResourceType;
import at.kaindorf.backend.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping
    public ResponseEntity<List<ResourceDTO>> getResource() {
        return ResponseEntity.ok(resourceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceDTO> getResourceById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(resourceService.findById(id));
    }

    @GetMapping("/available")
    public ResponseEntity<List<ResourceDTO>> getAllAvailableResources() {
        return ResponseEntity.ok(resourceService.findAvailableResources());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<ResourceDTO>> getAllResourcesByType(
            @PathVariable ResourceType type
    ){
        return ResponseEntity.ok(resourceService.findResourcesByType(type));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ResourceDTO>> getAllResourcesByName(
            @PathVariable String name
    ){
        return ResponseEntity.ok(resourceService.findResourcesByName(name));
    }

    @GetMapping("/inventory/{nr}")
    public ResponseEntity<ResourceDTO> getAllResourcesByInventoryNr(
            @PathVariable Long nr
    ){
        return ResponseEntity.ok(resourceService.findResourceByInventoryNr(nr));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteResource(
            @PathVariable Long id
    ){
        resourceService.deleteResource(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createResource(
            @RequestBody ResourceDTO resource
    ){
        return ResponseEntity.ok(resourceService.createNewResource(resource));
    }

    @PutMapping("/update/{id}")
    public void updateResource(
            @PathVariable Long id,
            @RequestBody ResourceDTO Resource
    ){
        resourceService.updateResource(id, Resource);
        ResponseEntity.ok();
    }
}

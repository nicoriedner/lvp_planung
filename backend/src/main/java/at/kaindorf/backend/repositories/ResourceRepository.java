package at.kaindorf.backend.repositories;

import at.kaindorf.backend.model.Resource;
import at.kaindorf.backend.model.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    @Query("SELECT r FROM Resource r WHERE r.id = ?1")
    Resource findResourceById(Long id);

    @Query("SELECT r FROM Resource r WHERE r.name = ?1")
    List<Resource> findResourceByName(String name);

    @Query("SELECT r FROM Resource r WHERE r.isAvailable = true ")
    List<Resource> findAvailableResources();

    @Query("SELECT r FROM Resource r WHERE r.resourceType = ?1")
    List<Resource> findResourceByTyp(ResourceType typ);

    @Query("SELECT r FROM Resource r WHERE r.inventoryNum = ?1")
    Resource findResourceByInventoryNum(Long inventoryNum);
}
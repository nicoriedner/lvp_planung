package at.kaindorf.backend.repositories;

import at.kaindorf.backend.model.Kompetenz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KompetenzRepository extends JpaRepository<Kompetenz, Long> {

    @Query("SELECT k FROM Kompetenz k WHERE k.id = ?1")
    Kompetenz findKompetenzById(Long id);

    @Query("SELECT k FROM Kompetenz k WHERE k.bezeichnung = ?1")
    Kompetenz findByBezeichnung(String bezeichnung);
}

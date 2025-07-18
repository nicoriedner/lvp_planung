package at.kaindorf.backend.repositories;

import at.kaindorf.backend.model.Kompetenz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface KompetenzRepository extends JpaRepository<Kompetenz, Long> {

    @Query("SELECT k FROM Kompetenz k WHERE k.name = ?1")
    Kompetenz findByName(String name);
}

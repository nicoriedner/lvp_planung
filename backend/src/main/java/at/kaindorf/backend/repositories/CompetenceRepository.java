package at.kaindorf.backend.repositories;

import at.kaindorf.backend.model.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {

    @Query("SELECT c FROM Competence c WHERE c.name = ?1")
    Competence findByName(String name);
}

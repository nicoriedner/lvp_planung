package at.kaindorf.backend.repositories;

import at.kaindorf.backend.model.Lehrgang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LehrgangRepository extends JpaRepository<Lehrgang, Long> {

    @Query("SELECT l FROM Lehrgang l WHERE l.id = ?1")
    Lehrgang findLehrgangById(Long id);

    @Query("SELECT l FROM Lehrgang l WHERE l.bezeichnung = ?1")
    Lehrgang findLehrgangByBezeichnung(String bezeichnung);

    @Query("SELECT l FROM Lehrgang l where l.dauer > ?1")
    Lehrgang findLehrgangByDauerGreaterThan(int dauer);

    @Query("SELECT l FROM Lehrgang l where l.dauer < ?1")
    Lehrgang findLehrgangByDauerLessThan(int dauer);

    @Query("SELECT l FROM Lehrgang l where l.dauer = ?1")
    List<Lehrgang> findLehrgaengeByDauer(int dauer);
}

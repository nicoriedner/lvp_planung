package at.kaindorf.backend.repositories;

import at.kaindorf.backend.model.Lehrsaal;
import at.kaindorf.backend.model.Schulungstermin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface LehrsaalRepository extends JpaRepository<Lehrsaal, Long> {

    @Query("SELECT l FROM Lehrsaal l WHERE l.id = ?1")
    Lehrsaal findLehrsaalById(Long id);

    @Query("SELECT l FROM Lehrsaal l WHERE l.ausstattung = ?1")
    List<Lehrsaal> findLehrsaalByAusstattung(String ausstattung);

    @Query("SELECT l FROM Lehrsaal l WHERE l.sitzPlaetze >= ?1")
    List<Lehrsaal> findLehrsaalBySitzPlaetzeGreaterThanEqual(Integer anzahl);

    @Query("SELECT l FROM Lehrsaal l WHERE l.bezeichnung = ?1")
    Lehrsaal findLehrsaalByBezeichnung(String bezeichnung);

    @Query("SELECT l FROM Lehrsaal l WHERE :schulungstermin MEMBER OF l.schulungstermine")
    List<Lehrsaal> findLehrsaalBySchulungstermine(Schulungstermin schulungstermin);
}
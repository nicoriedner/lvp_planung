package at.kaindorf.backend.repositories;

import at.kaindorf.backend.model.Lehrsaal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LehrsaalRepository extends JpaRepository<Lehrsaal, Long> {

    @Query("SELECT l FROM Lehrsaal l WHERE l.id = ?1")
    public Lehrsaal findLehrsaalById(Long id);

    @Query("SELECT l FROM Lehrsaal l WHERE l.ausstattung = ?1")
    public List<Lehrsaal> findLehrsaalByAusstattung(String ausstattung);

    @Query("SELECT l FROM Lehrsaal l WHERE l.sitzPlaetze > ?1")
    public List<Lehrsaal> findLehrsaalBySitzPlaetzeGreaterThan(Integer anzahl);
}

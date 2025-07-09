package at.kaindorf.backend.repositories;

import at.kaindorf.backend.model.Ressource;
import at.kaindorf.backend.model.RessourcenTyp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RessourceRepository extends JpaRepository<Ressource, Long> {

    @Query("SELECT r FROM Ressource r WHERE r.id = ?1")
    Ressource findRessourceById(Long id);

    @Query("SELECT r FROM Ressource r WHERE r.bezeichnung = ?1")
    List<Ressource> findRessourceByBezeichnung(String bezeichnung);

    @Query("SELECT r FROM Ressource r WHERE r.isAvailable = true ")
    List<Ressource> findAvailableRessources();

    @Query("SELECT r FROM Ressource r WHERE r.typ = ?1")
    List<Ressource> findRessourceByTyp(RessourcenTyp typ);

    @Query("SELECT r FROM Ressource r WHERE r.inventarNr = ?1")
    Ressource findRessourceByInventarNr(Long inventarNr);
}
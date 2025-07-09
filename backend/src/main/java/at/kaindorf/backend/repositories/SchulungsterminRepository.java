package at.kaindorf.backend.repositories;

import at.kaindorf.backend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.List;

public interface SchulungsterminRepository extends JpaRepository<Schulungstermin, Long> {

    @Query("SELECT s FROM Schulungstermin s WHERE s.id = ?1")
    Schulungstermin findSchulungsterminById(Long id);

    @Query("SELECT s FROM Schulungstermin s WHERE s.anzTeilnehmer > ?1")
    List<Schulungstermin> findSchulungsterminByAnzTeilnehmerGreaterThan(Integer anzTeilnehmer);

    @Query("SELECT s FROM Schulungstermin s WHERE s.anzTeilnehmer < ?1")
    List<Schulungstermin> findSchulungsterminByAnzTeilnehmerSmallerThan(Integer anzTeilnehmer);

    @Query("SELECT s FROM Schulungstermin s where s.anzTeilnehmer = ?1")
    List<Schulungstermin> findSchulungsterminByAnzTeilnehmer(Integer anzTeilnehmer);

    @Query("SELECT s FROM Schulungstermin s WHERE s.status = ?1")
    List<Schulungstermin> findSchulungsterminByStatus(Status status);

    @Query("SELECT s FROM Schulungstermin s WHERE s.startDatum > ?1")
    List<Schulungstermin> findSchulungsterminByStartDatumAfter(LocalDateTime startDatum);

    @Query("SELECT s FROM Schulungstermin s WHERE s.startDatum < ?1")
    List<Schulungstermin> findSchulungsterminByStartDatumBefore(LocalDateTime startDatum);

    @Query("SELECT s FROM Schulungstermin s WHERE s.startDatum = ?1")
    List<Schulungstermin> findSchulungsterminByStartDatum(LocalDateTime startDatum);

    @Query("SELECT s FROM Schulungstermin s WHERE s.endDatum > ?1")
    List<Schulungstermin> findSchulungsterminByEndDatumAfter(LocalDateTime endDatum);

    @Query("SELECT s FROM Schulungstermin s WHERE s.endDatum < ?1")
    List<Schulungstermin> findSchulungsterminByEndDatumBefore(LocalDateTime endDatum);

    @Query("SELECT s FROM Schulungstermin s WHERE s.endDatum = ?1")
    List<Schulungstermin> findSchulungsterminByEndDatum(LocalDateTime endDatum);

    @Query("SELECT s FROM Schulungstermin s WHERE s.startDatum >= ?1 AND s.endDatum <= ?2")
    List<Schulungstermin> findSchulungsterminByStartDatumAndEndDatumBefore(LocalDateTime startDatum, LocalDateTime endDatum);

    @Query("SELECT s FROM Schulungstermin s WHERE s.lehrsaal = ?1")
    List<Schulungstermin> findSchulungstermineByLehrsaal(Lehrsaal lehrsaal);

    @Query("SELECT s FROM Schulungstermin s WHERE s.lehrgang = ?1")
    List<Schulungstermin> findSchulungstermineByLehrgang(Lehrgang lehrgang);

    @Query("SELECT s FROM Schulungstermin s WHERE s.leiter = ?1")
    List<Schulungstermin> findSchulungstermineByLeiter(Person leiter);
}

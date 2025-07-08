package at.kaindorf.backend.repositories;

import at.kaindorf.backend.model.Lehrgang;
import at.kaindorf.backend.model.Lehrsaal;
import at.kaindorf.backend.model.Schulungstermin;
import at.kaindorf.backend.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SchulungsterminRepository extends JpaRepository<Schulungstermin, Long> {

    @Query("SELECT s FROM Schulungstermin s WHERE s.id = ?1")
    Schulungstermin findSchulungsterminById(Long id);

    @Query("SELECT s FROM Schulungstermin s WHERE s.anzTeilnehmer > ?1")
    Schulungstermin findSchulungsterminByAnzTeilnehmerGreaterThan(int anzTeilnehmer);

    @Query("SELECT s FROM Schulungstermin s WHERE s.anzTeilnehmer < ?1")
    Schulungstermin findSchulungsterminByAnzTeilnehmerSmallerThan(int anzTeilnehmer);

    @Query("SELECT s FROM Schulungstermin s where s.anzTeilnehmer = ?1")
    Schulungstermin findSchulungsterminByAnzTeilnehmer(int anzTeilnehmer);

    @Query("SELECT s FROM Schulungstermin s WHERE s.status = ?1")
    Schulungstermin findSchulungsterminByStatus(Status status);

    @Query("SELECT s FROM Schulungstermin s WHERE s.startDatum > ?1")
    Schulungstermin findSchulungsterminByStartDatumAfter(LocalDate startDatum);

    @Query("SELECT s FROM Schulungstermin s WHERE s.startDatum < ?1")
    Schulungstermin findSchulungsterminByStartDatumBefore(LocalDate startDatum);

    @Query("SELECT s FROM Schulungstermin s WHERE s.startDatum = ?1")
    Schulungstermin findSchulungsterminByStartDatum(LocalDate startDatum);

    @Query("SELECT s FROM Schulungstermin s WHERE s.endDatum > ?1")
    Schulungstermin findSchulungsterminByEndDatumAfter(LocalDate endDatum);

    @Query("SELECT s FROM Schulungstermin s WHERE s.endDatum < ?1")
    Schulungstermin findSchulungsterminByEndDatumBefore(LocalDate endDatum);

    @Query("SELECT s FROM Schulungstermin s WHERE s.endDatum = ?1")
    Schulungstermin findSchulungsterminByEndDatum(LocalDate endDatum);

    @Query("SELECT s FROM Schulungstermin s WHERE s.startDatum >= ?1 AND s.endDatum <= ?2")
    Schulungstermin findSchulungsterminByStartDatumAndEndDatumBefore(LocalDate startDatum, LocalDate endDatum);

    @Query("SELECT s FROM Schulungstermin s WHERE s.lehrsaal = ?1")
    Schulungstermin findSchulungsterminByLehrsaal(Lehrsaal lehrsaal);

    @Query("select s FROM Schulungstermin s WHERE s.lehrgang = ?1")
    Schulungstermin findSchulungsterminByLehrgang(Lehrgang lehrgang);
}

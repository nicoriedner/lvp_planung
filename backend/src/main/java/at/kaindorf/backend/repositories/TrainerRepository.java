package at.kaindorf.backend.repositories;

import at.kaindorf.backend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    @Query("SELECT p FROM Trainer p WHERE p.rank = ?1")
    List<Trainer> findTrainerByRank(Rank rank);

    @Query("SELECT p FROM Trainer p WHERE p.firstname = ?1")
    List<Trainer> findTrainerByFirstname(String firstName);

    @Query("SELECT p FROM Trainer p WHERE p.lastname = ?1")
    List<Trainer> findTrainerByLastname(String lastName);

    @Query("SELECT DISTINCT p FROM Trainer p JOIN p.competences c WHERE c IN ?1")
    List<Trainer> findTrainerByCompetences(@Param("competences") List<Competence> competences);

    @Query("SELECT p FROM Trainer p WHERE p.workmodel IN ?1")
    List<Trainer> findTrainerByWorkmodel(List<Workmodel> workmodel);

    @Query("SELECT p FROM Trainer p WHERE p.maximumWorkHours > ?1")
    List<Trainer> findTrainerByHoursGreaterThan(Double hours);

    @Query("SELECT p FROM Trainer p WHERE p.maximumWorkHours < ?1")
    List<Trainer> findTrainerByHoursLessThan(Double hours);

    @Query("SELECT p FROM Trainer p WHERE p.maximumWorkHours = ?1")
    List<Trainer> findTrainerByHours(Double hours);
}

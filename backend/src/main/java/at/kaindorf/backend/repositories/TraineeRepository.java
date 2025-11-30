package at.kaindorf.backend.repositories;

import at.kaindorf.backend.model.Person;
import at.kaindorf.backend.model.Rank;
import at.kaindorf.backend.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TraineeRepository extends JpaRepository<Trainee, Long> {
    @Query("SELECT p FROM Trainee p WHERE p.rank = ?1")
    List<Trainee> findTraineeByRank(Rank rank);

    @Query("SELECT p FROM Trainee p WHERE p.firstname = ?1")
    List<Trainee> findTraineeByFirstname(String firstName);

    @Query("SELECT p FROM Trainee p WHERE p.lastname = ?1")
    List<Trainee> findTraineeByLastname(String lastName);
}

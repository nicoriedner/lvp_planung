package at.kaindorf.backend.repositories;

import at.kaindorf.backend.model.Competence;
import at.kaindorf.backend.model.Person;
import at.kaindorf.backend.model.Rank;
import at.kaindorf.backend.model.Weekmodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p FROM Person p WHERE p.rank = ?1")
    List<Person> findPersonByRank(Rank rank);

    @Query("SELECT p FROM Person p WHERE p.firstName = ?1")
    List<Person> findPersonByFirstName(String firstName);

    @Query("SELECT p FROM Person p WHERE p.lastName = ?1")
    List<Person> findPersonByLastName(String lastName);

    @Query("SELECT DISTINCT p FROM Person p JOIN p.competences k WHERE k IN ?1")
    List<Person> findPersonsByCompetences(@Param("competences") List<Competence> competences);

    @Query("SELECT p FROM Person p WHERE p.weekmodel IN ?1")
    List<Person> findPersonByWeekmodel(List<Weekmodel> weekmodel);

    @Query("SELECT p FROM Person p WHERE p.hours > ?1")
    List<Person> findPersonByHoursGreaterThan(Double hours);

    @Query("SELECT p FROM Person p WHERE p.hours < ?1")
    List<Person> findPersonByHoursLessThan(Double hours);

    @Query("SELECT p FROM Person p WHERE p.hours = ?1")
    List<Person> findPersonByHours(Double hours);
}

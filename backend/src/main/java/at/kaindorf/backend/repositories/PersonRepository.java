package at.kaindorf.backend.repositories;

import at.kaindorf.backend.model.Dienstgrad;
import at.kaindorf.backend.model.Kompetenz;
import at.kaindorf.backend.model.Person;
import at.kaindorf.backend.model.Wochenmodell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p FROM Person p")
    List<Person> findAllPersons();

    @Query("SELECT p FROM Person p WHERE p.id = ?1")
    public Person findPersonById(Long id);

    @Query("SELECT p FROM Person p WHERE p.dienstgrad = ?1")
    public List<Person> findPersonByDienstgrad(Dienstgrad dienstgrad);

    @Query("SELECT p FROM Person p WHERE p.firstName = ?1")
    public List<Person> findPersonByFirstName(String firstName);

    @Query("SELECT p FROM Person p WHERE p.lastName = ?1")
    public List<Person> findPersonByLastName(String lastName);

    @Query("SELECT DISTINCT p FROM Person p JOIN p.kompetenzen k WHERE k IN :kompetenzen")
    List<Person> findPersonsByKompetenzen(@Param("kompetenzen") List<Kompetenz> kompetenzen);

    @Query("SELECT p FROM Person p WHERE p.wochenmodell = ?1")
    public List<Person> findPersonByWochenmodell(List<Wochenmodell> wochenmodell);

    @Query("SELECT p FROM Person p WHERE p.stunden > ?1")
    public List<Person> findPersonByStundenGreaterThan(double stunden);

    @Query("SELECT p FROM Person p WHERE p.stunden < ?1")
    public List<Person> findPersonByStundenLessThan(double stunden);

    @Query("SELECT p FROM Person p WHERE p.stunden = ?1")
    public List<Person> findPersonByStunden(double stunden);
}

package at.kaindorf.backend.repositories;

import at.kaindorf.backend.model.Account;
import at.kaindorf.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import javax.accessibility.AccessibleComponent;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {


    Optional<Account> findByUsername(String username);

    Optional<Account> findByBirthdate(LocalDate birthdate);

    Optional<Account> findByEmail(String email);

    Optional<Account> findById(Long id);

    List<Account> findByFirstName(String firstname);

    List<Account> findByLastName(String lastName);

    List<Account> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT a FROM Account a WHERE :adminRole MEMBER OF a.roles")
    List<Account> findByAdmin(@Param("adminRole") Role admin);

    @Query("SELECT a FROM Account a WHERE :adminRole NOT MEMBER OF a.roles")
    List<Account> findByIsNotAdmin(@Param("adminRole") Role admin);

    @Query("SELECT a FROM Account a WHERE :role MEMBER OF a.roles")
    List<Account> findByRole(@Param("role") Role role);

    Optional<Account> findByEmailOrUsername(String email, String username);

    List<Account> findByBirthdateBetween(LocalDate startDate, LocalDate endDate);

}

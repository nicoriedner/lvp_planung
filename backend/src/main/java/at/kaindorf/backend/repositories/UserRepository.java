package at.kaindorf.backend.repositories;

import at.kaindorf.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findUserByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.birthdate = ?1")
    User findUserByBirthdate(LocalDate birthdate);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User findUserById(Integer id);

    @Query("SELECT u FROM User u WHERE u.isAdmin")
    List<User> findAdminUsers();

    @Query("SELECT u FROM User u WHERE u.isAdmin = false")
    List<User> findNotAdminUsers();

    @Query("SELECT u FROM User u WHERE u.lastName = ?1")
    List<User> findUsersByLastName(String lastName);

    @Query("SELECT u FROM User u WHERE u.firstName = ?1 AND u.lastName = ?2")
    List<User> findUsersByFirstAndLastName(String firstName, String lastName);
}

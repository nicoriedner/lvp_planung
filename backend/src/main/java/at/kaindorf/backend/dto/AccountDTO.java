package at.kaindorf.backend.dto;

import at.kaindorf.backend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String passwordhash;
    private String email;
    private LocalDate birthdate;
    private Set<Role> roles = new HashSet<>();
    private String avatar;
}

package at.kaindorf.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String passwordhash;
    private String email;
    private LocalDate birthdate;
    private boolean isAdmin;
    private String avatar;
}

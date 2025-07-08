package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.UserDTO;
import at.kaindorf.backend.mapper.UserMapper;
import at.kaindorf.backend.model.User;
import at.kaindorf.backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User register(String username, String password, String email, LocalDate birthdate, String avatar, String firstName, String lastName) {
        if (userRepository.findUserByUsername(username) != null) {
            throw new RuntimeException("Name bereits vorhanden");
        }

        if (userRepository.findUserByEmail(email) != null) {
            throw new RuntimeException("Email bereits vorhanden");
        }

        User user = new User();
        user.setUsername(username);
        user.setPasswordhash(hashPassword(password));
        user.setEmail(email);
        user.setBirthdate(birthdate);
        user.setAvatar(avatar);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        User savedUser = userRepository.save(user);

        return savedUser;
    }

    private String hashPassword(String password) {
        String hashedPassword = "";

        return hashedPassword;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDTO)
                .toList();
    }
}
